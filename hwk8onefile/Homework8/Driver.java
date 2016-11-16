package Homework8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.out;

class Driver {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        out.print("Enter template filename: ");
        Template t = loadTemplate(scanner.nextLine());
        out.print("Enter dictionary filename: ");
        Dictionary d = loadDictionary(scanner.nextLine());
        out.print("Enter output filename: ");
        try (OutputStream os = new FileOutputStream(new File(scanner.nextLine()))) {
            os.write(t.fill(d).getBytes());
        } catch (Homework8Exception e) {
            out.println("lmao");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static Template loadTemplate(String filename) {
        try (InputStream is = new FileInputStream(new File(filename))) {
            return Template.fromInputStream(is);
        } catch (Homework8Exception e) {
            // do nothing
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return new Template("");
    }

    private static Dictionary loadDictionary(String filename) {
        try (InputStream is = new FileInputStream(new File(filename))) {
            return Dictionary.fromInputStream(is);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return new Dictionary();
    }
}
