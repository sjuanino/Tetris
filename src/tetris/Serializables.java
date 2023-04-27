package tetris;

import java.io.*;

/**
 * Esta clase serializa y deserializa un objeto a un archivo de texto Se utiliza
 * para implementar la persistencia de datos
 */
public class Serializables implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Metodo al que se le pasa un objeto y lo serializa en un fichero de texto
     */
    public static void serializar(Object obj, String fileName) {
        try {
            FileOutputStream fs = new FileOutputStream(fileName);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(obj);
            fs.close();
            os.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO ERROR");
            e.printStackTrace();
        }
    }

    /**
     * Metodo al que se le da el nombre de un fichero y lo deserializa en un
     * objeto.
     */
    public static Object deserializar(String fileName) {
        Object obj = new Object();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            obj = ois.readObject();
            ois.close();
            fis.close();
        } catch (ClassNotFoundException e) {
            System.out.println("CLASS NOT FOUND");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO ERROR");
        }
        return obj;
    }

} // Fin Clase Serializables

