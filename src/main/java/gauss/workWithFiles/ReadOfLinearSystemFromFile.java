package gauss.workWithFiles;

import gauss.expressions.ExpressionImpl;
import gauss.linearsystems.LinearSystem;
import gauss.util.ParserToLinearSystemUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Этот класс читает из файла систему линейных уравнений
 */
public class ReadOfLinearSystemFromFile implements FileReader {
    private String pathFrom;

    public ReadOfLinearSystemFromFile(String pathFrom) {
        this.pathFrom = pathFrom;
    }

    public String getPath() {
        return pathFrom;
    }

    public void setPath(String path) {
        this.pathFrom = path;
    }

    /**
     * Используем этот метод для чтениия системы.
     * В методе используется парсер java.gauss.util.ParserToLinearSystem.
     * @return - возвращает систему линейных ур-ний.
     */
    public LinearSystem<Double, ExpressionImpl> readLinearSystem() {
        List<String> strings = null;
        try {
            strings = read();
        } catch (IOException e) {
            strings = new ArrayList<>();
            e.printStackTrace();
        }
        return ParserToLinearSystemUtil.pars(strings);
    }

    @Override
    public List<String> read() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(this.pathFrom), Charset.defaultCharset());
        return lines;
    }
}
