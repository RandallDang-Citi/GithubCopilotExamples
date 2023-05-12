package tf;

import java.util.*;
import java.util.stream.Collectors;

public class Tf {

    private static List<Client> buy;
    private static List<Client> sell;

    public static void main(String[] args) {

        List<Client> butAon = buy.stream().filter(client -> client.getAon()).collect(Collectors.toList());
        butAon.sort(Comparator.comparing(Client::getSize));
        List<Client> sellAon = sell.stream().filter(client -> client.getAon()).collect(Collectors.toList());
        List<Client> butNotAon = buy.stream().filter(client -> !client.getAon()).collect(Collectors.toList());
        List<Client> sellNotAon = sell.stream().filter(client -> !client.getAon()).collect(Collectors.toList());
        sellNotAon.sort(Comparator.comparing(Client::getSize));
        Map<Client,List<Client>> complete = new HashMap<>();
        for (int i=0;i < butAon.size();i++){
            for (int j=0;j < sellAon.size();j++){
                if (butAon.get(i).getSize() == sellAon.get(j).getSize()){
                    complete.put(butAon.get(i), Collections.singletonList(sellAon.get(j)));
                    sellAon.remove(sellAon.get(j));
                    butAon.remove(butAon.get(i));
                    i--;
                }
            }
        }

        //从sellNotAon中找出匹配的butAon
        for (int i=0;i < butAon.size();i++){
            for (int j=0;j < sellNotAon.size();j++){
                if (butAon.get(i).getSize() <= sellNotAon.get(j).getSize()){
                    int size = sellNotAon.get(j).getSize() - butAon.get(i).getSize();
                    if(size == 0){
                        complete.put(butAon.get(i), Collections.singletonList(sellNotAon.get(j)));
                        sellNotAon.remove(sellNotAon.get(j));
                        butAon.remove(butAon.get(i));
                        i--;
                    }else {
                        complete.put(butAon.get(i), Collections.singletonList(sellNotAon.get(j)));
                        sellNotAon.get(j).setSize(size);
                        i--;
                    }
                }
            }
        }

        for (int i=0;i < butNotAon.size();i++){
            List<Client> clients = subsetSum(sellAon, 0, butNotAon.get(i).getSize());
            sellAon.removeAll(clients);
            complete.put(butNotAon.get(i),clients);
            butNotAon.remove(butNotAon.get(i));
            i--;
        }

        //排序butNotAon
        butNotAon.sort(Comparator.comparing(Client::getSize));



    }


    //求集合中是否有子集合的和等于sum,返回子集合
    public static List<Client> subsetSum(List<Client> arr, int n, int sum) {
        if (sum == 0) {
            return new ArrayList<>();
        }
        if (n == 0 && sum != 0) {
            return null;
        }
        if (arr.get(n-1).getSize() > sum) {
            return subsetSum(arr, n - 1, sum);
        }
        List<Client> list = subsetSum(arr, n - 1, sum);
        if (list != null) {
            return list;
        } else {
            list = subsetSum(arr, n - 1, sum - arr.get(n-1).getSize());
            if (list != null) {
                list.add(arr.get(n-1));
                return list;
            } else {
                return null;
            }
        }
    }



}
