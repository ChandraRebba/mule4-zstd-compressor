package org.mule.extension.internal.zstd;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;
import com.github.luben.zstd.Zstd;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class ZstandardOperations {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZstandardOperations.class);

    /**
     * Compresses the payload using Zstandard.
     *
     * @param data The byte array content to compress.
     * @param level The compression level (1-22). Defaults to 3.
     * @return The compressed byte array.
     */
    @DisplayName("Compress")
    @MediaType(value = ANY, strict = false)
    public byte[] compress(@Content byte[] data, @Optional(defaultValue = "3") int level) {
        LOGGER.debug("Starting Zstandard compression with level [{}]. Input size: {} bytes.", level, data.length);
        byte[] compressedData = Zstd.compress(data, level);
        LOGGER.debug("Finished compression. Output size: {} bytes.", compressedData.length);
        return compressedData;
    }

    /**
     * Decompresses the payload using Zstandard.
     *
     * @param data The compressed byte array to decompress.
     * @return The decompressed byte array.
     */
    @DisplayName("Decompress")
    @MediaType(value = ANY, strict = false)
    public byte[] decompress(@Content byte[] data) {
        LOGGER.debug("Starting Zstandard decompression. Input size: {} bytes.", data.length);
        long originalSize = Zstd.decompressedSize(data);
        if (originalSize > Integer.MAX_VALUE) {
            String errorMessage = "Decompressed content is too large for a byte array (> 2GB)";
            LOGGER.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        byte[] decompressedData = Zstd.decompress(data, (int) originalSize);
        LOGGER.debug("Finished decompression. Output size: {} bytes.", decompressedData.length);
        return decompressedData;
    }
}
