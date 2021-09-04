package gauss.workWithFiles;

import java.io.IOException;

/**
 * Класс, который реализует этот интерфейс, должен производить запись обьктов ив файл,
 * каких именно это он определяется в классе, а как именно в реализации.
 */
public interface FileWriter {
    void write(Object o) throws IOException;
}
