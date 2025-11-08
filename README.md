# Zstandard Extension for Mule

This Mule extension provides operations to compress and decompress data using the Zstandard (Zstd) algorithm. Zstandard is a fast lossless compression algorithm, targeting real-time compression scenarios at zlib-level and better compression ratios.

## Operations

The extension provides two main operations:

*   **Compress**: Compresses the input payload using Zstandard. It takes an optional `level` parameter to control the compression ratio (from 1 to 22). The default level is 3.
*   **Decompress**: Decompresses a Zstandard-compressed payload.

## How the Connector Works

The connector uses the `com.github.luben:zstd-jni` library to perform the compression and decompression operations. The `ZstandardOperations` class contains the `compress` and `decompress` methods, which are exposed as Mule operations.



The `compress` operation takes a byte array as input and returns a compressed byte array.

<img width="616" height="462" alt="image" src="https://github.com/user-attachments/assets/a547b77a-3b9a-4ce5-a096-9c191deca8dd" />

The `decompress` operation takes a compressed byte array and returns the original decompressed data.

<img width="607" height="418" alt="image" src="https://github.com/user-attachments/assets/1cf83dda-41cc-497b-8b4c-78de1e0a8902" />


## Zstandard vs. GZIP

Zstandard was designed to be a successor to GZIP. Here's a quick comparison:

| Feature            | Zstandard                                       | GZIP                                            |
| ------------------ | ----------------------------------------------- | ----------------------------------------------- |
| **Speed**          | Significantly faster, especially for decompression. | Slower than Zstandard.                          |
| **Compression Ratio** | Generally better compression ratio at the same speed. | Good compression, but often outperformed by Zstd. |
| **Compression Levels** | Wide range of levels (1-22) for fine-tuning. | Fewer levels (1-9).                             |
| **Use Cases**      | Real-time applications, big data, and scenarios where both speed and ratio are important. | General-purpose compression, widely supported. |

In summary, Zstandard offers better performance and flexibility compared to GZIP, making it a great choice for modern applications.

## Building

To build the connector, you can use the following Maven command:

```bash
mvn clean package
```
