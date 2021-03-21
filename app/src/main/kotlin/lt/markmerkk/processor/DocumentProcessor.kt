package lt.markmerkk.processor

import java.io.File

/**
 * Imagined document processor
 */
class DocumentProcessor {

    /**
     * Processes document and outputs read value in it
     * @param imagePath document path in file system
     * @return document read value or empty
     */
    fun readDocument(imageFile: File): String {
        return "1234.5"
    }
}