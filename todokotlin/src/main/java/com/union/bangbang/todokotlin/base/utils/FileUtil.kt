package com.union.bangbang.todokotlin.base.utils

import android.os.Environment
import android.util.Log
import java.io.File
import java.util.regex.Pattern

/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/22 8:00 PM
 * 只有编译器可能不骗你。
 */
object FileUtil {
    val httpPattern = Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~/])+$")

    /*
        storage/emulated/0
     */
    fun sdCardIsAvailable(): Boolean {
        return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            val sd = File(Environment.getExternalStorageDirectory().path);
            Log.e("qq", "sd = $sd")
            sd.canWrite()
        } else {
            false
        }
    }

    fun isWebUrl(s: String) = httpPattern.matcher(s).matches()

    /* fun fileTobyte(file: File): ByteArray? {
         var buffer: ByteArray? = null
         try {
             val fis = FileInputStream(file)
             val bos = ByteArrayOutputStream()
             val b = ByteArray(1024)
             var n: Int

             while ((n=fis.read(b))!=-1) {
                 bos.write(b, 0, n)
             }

             fis.close()
             bos.close()
             buffer = bos.toByteArray()
         } catch (e: FileNotFoundException) {
             e.printStackTrace()
         } catch (e: IOException) {
             e.printStackTrace()
         }

         return buffer
     }
 */
}