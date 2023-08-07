import java.io.File;
import java.util.Objects;

public class GetFiles {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\mamon\\YandexDisk\\Программирование\\Проекты\\module4_spring_without_boot\\src\\main\\java\\ru\\practicum\\item");
        for (File f : Objects.requireNonNull(file.listFiles())) {
            f.delete();
        }
    }
}
