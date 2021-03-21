package lt.markmerkk

import org.testng.Reporter
import java.awt.AlphaComposite
import java.awt.Color
import java.awt.Dimension
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.lang.StringBuilder
import javax.imageio.ImageIO

object ReportUtils {

    fun reportImages(imageFiles: List<File>) {
        Reporter.log(wrapImageFilesAsTable(imageFiles))
    }

    fun reportMessage(message: String) {
        Reporter.log("<p>$message</p>")
    }

    fun reportErrors(errors: List<Throwable>) {
        Reporter.log(wrapErrorsAsTable(errors))
    }

    fun wrapErrorsAsTable(errors: List<Throwable>): String {
        val reportTDErrors = errors
            .mapIndexed { index, error -> "<tr><td>${index}</td><td>${error.message}</td></tr>" }
        val sb = StringBuilder()
            .append("<table border='1'>")
            .append("<th><td>Error</td></th>")
            .append(reportTDErrors.joinToString("\n"))
            .append("</tr>")
            .append("</table>")
        return sb.toString()
    }

    private fun wrapImageFilesAsTable(imageFiles: List<File>): String {
        val reportImages = imageFiles
            .map { createReportFile(it) }
        val reportTDImageNames = reportImages
            .map { "<td>${it.nameWithoutExtension}</td>" }
        val reportTDImages = reportImages
            .map { "<td><img src='screenshots/${it.name}'/></td>" }
        val sb = StringBuilder()
            .append("<table border='1'>")
            .append("<tr>")
            .append(reportTDImageNames.joinToString("\n"))
            .append("</tr>")
            .append("<tr>")
            .append(reportTDImages.joinToString("\n"))
            .append("</tr>")
            .append("</table>")
        return sb.toString()
    }

    private fun createReportFile(imageFile: File): File {
        val inputImage = readImageFromFile(imageFile.absolutePath)
        val inputDimen = Dimension(inputImage.width, inputImage.height)
        val targetDimen = Dimension(1024, 1024)
        val scaleDimen = calcScaledDimension(inputDimen, targetDimen)
        val resizeImage = resize(inputImage, scaleDimen.width, scaleDimen.height)
        val targetDir = File("test-reports1/screenshots").apply {
            if (!exists())
                mkdirs()
        }
        val targetFile = storeImageToFile(
            resizeImage,
            targetDir,
            "${System.currentTimeMillis()}-${imageFile.nameWithoutExtension}"
        )
        return targetFile
    }

    /**
     * we want the x and o to be resized when the JFrame is resized
     *
     * @param originalImage an x or an o. Use cross or oh fields.
     *
     * @param biggerWidth
     * @param biggerHeight
     */
    private fun resize(
        originalImage: BufferedImage,
        newWidth: Int,
        newHeight: Int
    ): BufferedImage {
        val type = BufferedImage.TYPE_INT_ARGB
        val resizedImage = BufferedImage(newWidth, newHeight, type)
        val g = resizedImage.createGraphics()
        g.composite = AlphaComposite.Src
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, Color.BLACK, null)
        g.dispose()
        return resizedImage
    }

    fun calcScaledDimension(
        imgSize: Dimension,
        boundary: Dimension
    ): Dimension {
        val originalWidth = imgSize.width
        val originalHeight = imgSize.height
        val boundWidth = boundary.width
        val boundHeight = boundary.height
        var newWidth = originalWidth
        var newHeight = originalHeight
        if (originalWidth > boundWidth) {
            newWidth = boundWidth
            newHeight = newWidth * originalHeight / originalWidth
        }
        if (newHeight > boundHeight) {
            newHeight = boundHeight
            newWidth = newHeight * originalWidth / originalHeight
        }
        return Dimension(newWidth, newHeight)
    }

    /**
     * Opens, closes the stream after reading
     */
    @Throws(IllegalArgumentException::class)
    fun readImageFromFile(inputFilePath: String): BufferedImage {
        try {
            val inputFile = File(inputFilePath)
            return inputFile.inputStream()
                .use { isInput -> ImageIO.read(isInput) }
        } catch (e: IOException) {
            throw IllegalArgumentException("Cannot read image", e)
        }
    }

    @Throws(IllegalArgumentException::class)
    fun storeImageToFile(
        image: BufferedImage,
        outputDir: File,
        fileNameWithoutExt: String
    ): File {
        val outFile = File(outputDir, "${fileNameWithoutExt}.png")
        ImageIO.write(image, "png", outFile)
        return outFile
    }

}