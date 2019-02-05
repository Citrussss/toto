package com.union.bangbang.zero.util.photo;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.union.bangbang.zero.AppUtil;

import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposable;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @name zero
 * @anthor bangbang QQ:740090077
 * @time 2018/10/31 8:27 PM
 * 只有编译器可能不骗你。
 */
public class RxFileViewerFragment extends Fragment {
    /**
     * 选择图片
     */
    public final static String IMAGE = "image/*";
    /**
     * 选择音频
     */
    public final static String AUDIO = "audio/*";
    /**
     * 选择视频 （mp4 3gp 是android支持的视频格式）
     */
    public final static String VIDEO = "video/*";
    /**
     * 同时选择视频和图片
     */
    public final static String VIDEO_IMAGE = "video/*;image/*";
    /**
     * 无类型限制
     */
    public final static String ALL = "*/*";
    /**
     * 拍照咯
     */
    public final static String TAKE_PHOTO = "none";
    private static final int REQUEST_CAMERA = 0x16;
    private static final int REQUEST_VIEWER = 0x15;
    private @RxFileViewerFragment.FileType
    String type;
    private static File photoFile;
    private ObservableEmitter<File> emitter;

    public RxFileViewerFragment selectFile(@NonNull ObservableEmitter<File> emitter) {
        this.emitter = emitter;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!TAKE_PHOTO.equals(type)) {
//            openFileManager(type);
//        } else {
////            photoFile = takePhoto();
//        }
////        setContentView(null);
    }
    public void openFileManager(@RxFileViewerFragment.FileType String type) {
        Disposable subscribe = new RxPermissions((FragmentActivity) AppUtil.peekActivity())
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe((aBoolean -> {
                    if(aBoolean){Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType(type);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        startActivityForResult(intent, REQUEST_VIEWER);}
                }), Throwable::printStackTrace);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {
            try {
                emitter.onNext(photoFile);
            } catch (Exception e) {
                emitter.onError(e);
            }
        }
        //是否选择，没选择就不会继续
        else if (requestCode == REQUEST_VIEWER && resultCode == Activity.RESULT_OK) {
            //得到uri，后面就是将uri转化成file的过程。
            Uri uri = data.getData();
            String[] proj = {MediaStore.Images.Media.DATA};
            if (uri != null) {
                try {
                    String path = UriTofilePath.getFilePathByUri(getContext(), uri);
                    emitter.onNext(new File(path));
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }
        emitter.onComplete();
    }

    private static class UriTofilePath {
        public static String getFilePathByUri(Context context, Uri uri) {
            String path = null;
            // 以 file:// 开头的
            if (ContentResolver.SCHEME_FILE.equals(uri.getScheme())) {
                path = uri.getPath();
                return path;
            }
            // 以 content:// 开头的，比如 content://media/extenral/images/media/17766
            if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme()) && Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        if (columnIndex > -1) {
                            path = cursor.getString(columnIndex);
                        }
                    }
                    cursor.close();
                }
                return path;
            }
            // 4.4及之后的 是以 content:// 开头的，比如 content://com.android.providers.media.documents/document/image%3A235700
            if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme()) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (DocumentsContract.isDocumentUri(context, uri)) {
                    if (isExternalStorageDocument(uri)) {
                        // ExternalStorageProvider
                        final String docId = DocumentsContract.getDocumentId(uri);
                        final String[] split = docId.split(":");
                        final String type = split[0];
                        if ("primary".equalsIgnoreCase(type)) {
                            path = Environment.getExternalStorageDirectory() + "/" + split[1];
                            return path;
                        }
                    } else if (isDownloadsDocument(uri)) {
                        // DownloadsProvider
                        final String id = DocumentsContract.getDocumentId(uri);
                        final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                                Long.valueOf(id));
                        path = getDataColumn(context, contentUri, null, null);
                        return path;
                    } else if (isMediaDocument(uri)) {
                        // MediaProvider
                        final String docId = DocumentsContract.getDocumentId(uri);
                        final String[] split = docId.split(":");
                        final String type = split[0];
                        Uri contentUri = null;
                        if ("image".equals(type)) {
                            contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        } else if ("video".equals(type)) {
                            contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        } else if ("audio".equals(type)) {
                            contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                        }
                        final String selection = "_id=?";
                        final String[] selectionArgs = new String[]{split[1]};
                        path = getDataColumn(context, contentUri, selection, selectionArgs);
                        return path;
                    }
                }
            }
            return null;
        }

        private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
            Cursor cursor = null;
            final String column = "_data";
            final String[] projection = {column};
            try {
                cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
                if (cursor != null && cursor.moveToFirst()) {
                    final int column_index = cursor.getColumnIndexOrThrow(column);
                    return cursor.getString(column_index);
                }
            } finally {
                if (cursor != null)
                    cursor.close();
            }
            return null;
        }

        private static boolean isExternalStorageDocument(Uri uri) {
            return "com.android.externalstorage.documents".equals(uri.getAuthority());
        }

        private static boolean isDownloadsDocument(Uri uri) {
            return "com.android.providers.downloads.documents".equals(uri.getAuthority());
        }

        private static boolean isMediaDocument(Uri uri) {
            return "com.android.providers.media.documents".equals(uri.getAuthority());
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            RxFileViewerFragment.IMAGE,
            RxFileViewerFragment.AUDIO,
            RxFileViewerFragment.VIDEO,
            RxFileViewerFragment.VIDEO_IMAGE,
            RxFileViewerFragment.ALL,
            RxFileViewerFragment.TAKE_PHOTO
    })
    public @interface FileType {
    }

}
