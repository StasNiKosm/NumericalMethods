package gauss.workWithFiles;

import java.io.IOException;

/**
 * Класс, который реализует этот интерфейс, должен производить чтение обьктов из файла,
 * каких именно это он определяется в классе, а как именно в реализации.
 */
public interface FileReader {
    Object read() throws IOException;
}
