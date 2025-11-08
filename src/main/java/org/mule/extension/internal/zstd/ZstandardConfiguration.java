package org.mule.extension.internal.zstd;

import org.mule.runtime.extension.api.annotation.Operations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(ZstandardOperations.class)
public class ZstandardConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZstandardConfiguration.class);
}
