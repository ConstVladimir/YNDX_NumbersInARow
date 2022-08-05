package kvv.laptop;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Integer [] nums = {10, 3, 3, 2, 1, 1, 1, 2, 2, 3, 3,6,6,4,4,4,6,10,1,1,1,10,7}; //массив чисел

        ArrayDeque<Integer> numD = new ArrayDeque(nums.length); //очередь конечного результата

        int count = -1; //ведение кол-ва текущих одинаковых чисел
        Integer prevNumb = 0; // хранение предыдущего числа
        for (Iterator<Integer> it = Arrays.stream(nums).iterator();it.hasNext();) {
            Integer i = it.next();
            if (numD.isEmpty())count=1; //первое число из массива
            else {
                if (i.equals(prevNumb)) count++; //совпадение с предыдущим
                else if (count > 2){ //если одинакоых чисел больше трех, а следующее число отличное
                    for (;count>0;--count){ // удаление одинаковых чисел
                        numD.removeLast();
                        if (count ==1 ){ // обновление инф-ции о последнем и предпоследнем числе в записанной очереди
                            if (numD.size()>1){ //если чисел больше-равно 2
                                prevNumb = numD.removeLast();
                                if (prevNumb.equals(i)){
                                    if (prevNumb.equals(numD.getLast())) count = 3; //если два посл числа одиноковые, то теперь их три
                                    else count = 2; // равные текущее и последнее после удаления числа
                                }
                                else count = 1; // текущее не равно последнему после удаления
                                numD.addLast(prevNumb);
                            }
                            else if (numD.size()==1){ //если в очереди после удаления одно число
                                prevNumb=numD.getLast();
                                if (prevNumb.equals(i)) count = 2; // если равны с текущим
                                else count = 1; //если не равны
                            }
                            break;
                        }
                    }
                }
                else count = 1;
            }
            numD.addLast(i); //в конце добавляем текущее число в конечную очередь
            prevNumb=i;
        }
        System.out.println(numD.stream().map(t->t.toString()).reduce("",(e1,e2)->e1+e2+" "));
    }
}