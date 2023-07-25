package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProviderData {

    @DataProvider
    public Iterator<Object[]> userModelListDTO() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{User.builder()
                .email("liora@gmail.com")
                .password("$Liora12345")
                .build()});
        list.add(new Object[]{User.builder()
                .email("liora@gmail.com")
                .password("$Liora12345")
                .build()});
        list.add(new Object[]{User.builder()
                .email("liora@gmail.com")
                .password("$Liora12345")
                .build()});
        return list.iterator();

    }

    @DataProvider
    public Iterator<Object[]> regUserModelDTO() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{User.builder()
                .name("John")
                .lastname("Snow")
                .email("snow" + i + "@gmail.com")
                .password("$Asdf1234")
                .build()});
        return list.iterator();

    }

    @DataProvider
    public Iterator<Object[]> loginUserModelDTO_CSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/loginData.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{User.builder()
                    .email(split[0])
                    .password(split[1])
                    .build()});
            line = reader.readLine();
        }
        return list.iterator();
    }






}
