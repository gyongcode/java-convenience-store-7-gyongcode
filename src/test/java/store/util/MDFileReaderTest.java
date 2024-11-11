package store.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.model.Product;

class MDFileReaderTest {


    @DisplayName("MDFileReader 생성자 테스트")
    @Test
    void MDFileReader_생성자_테스트() {
        MDFileReader mdFileReader = new MDFileReader("/resources/products.md");
        assertThatThrownBy(() -> new MDFileReader("")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new MDFileReader(null)).isInstanceOf(
            IllegalArgumentException.class);
    }

    @DisplayName("getLines 테스트")
    @Test
    void getLines_테스트() throws IOException {
        MDFileReader mdFileReader = new MDFileReader("src/main/resources/products.md");
        assertThat(mdFileReader.getLines().size()).isEqualTo(18);

        MDFileReader notFoundFile = new MDFileReader("src/main/resources/notFound.md");
        assertThatThrownBy(() -> notFoundFile.getLines()).isInstanceOf(FileNotFoundException.class);

        MDFileReader EmptyFile = new MDFileReader("src/main/resources/EmptyFile.md");
        assertThatThrownBy(() -> EmptyFile.getLines()).isInstanceOf(IOException.class);
    }
}