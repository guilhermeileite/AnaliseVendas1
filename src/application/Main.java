package application;

import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Entre com o nome do local do arquivo: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Sale> list = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Sale(Integer.parseInt(fields[0]),
                        Integer.parseInt(fields[1]),
                        fields[2],
                        Integer.parseInt(fields[3]),
                        Double.parseDouble(fields[4])));
                line = br.readLine();
            }

            System.out.println("Cinco primeiras vendas de maior preço médio em 2016");
            List<Sale> newList = list.stream().filter(b -> b.getYear() == 2016).sorted((s1, s2) -> s2.averagePrice().compareTo(s1.averagePrice())).limit(5).collect(Collectors.toList());

            newList.forEach(System.out::println);

            double sum = list.stream().filter(a -> a.getMonth() == 1).filter(b -> b.getSeller().equals("Logan")).map(c -> c.getTotal()).reduce(0.0, (x,y) -> x + y);

            double sum2 = list.stream().filter(a -> a.getMonth() == 7).filter(b -> b.getSeller().equals("Logan")).map(c -> c.getTotal()).reduce(0.0, (x,y) -> x + y);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}