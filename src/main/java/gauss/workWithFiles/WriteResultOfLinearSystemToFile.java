package gauss.workWithFiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Этот класс аписывает в файл решение системы линейных уравнений.
 * ВАЖНО если файла не существует, то он создастся в указанной директории.
 * Файл будет перезаписываться.
 */
public class WriteResultOfLinearSystemToFile implements FileWriter {
    private String pathTo;

    public WriteResultOfLinearSystemToFile(String pathTo) {
        this.pathTo = pathTo;
    }

    public String getPathTo() {
        return pathTo;
    }

    public void setPathTo(String pathTo) {
        this.pathTo = pathTo;
    }


    @Override
    public void write(Object o) throws IOException {
        int n = 0;
        try(BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(new File(this.pathTo)))) {
            if(o == null){
                bw.write("UNDEFINED");
                return;
            }
            n = (int) o.getClass().getDeclaredMethod("size").invoke(o);
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < n; i++) {
                str.append(o.getClass().getDeclaredMethod("get", Integer.TYPE).invoke(o, i) + " ");
            }
            bw.write(str.toString());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("param isn't a list ");
            e.printStackTrace();
        }
    }

}
