// dev branch for Y.Practicum

import java.util.Scanner;
import java.lang.Math;

// Создаем класс заказа с полями (блюдо, цена блюда)
class Order
{
    public String dishInOrder;
    public double costOfTheDish;

    //создаем в классе конструктор, чтобы мог вернуть несколько значений блюда	(order.название, order.цена)
    public Order(String dishInOrder, double costOfTheDish)
    {
        this.dishInOrder = dishInOrder;
        this.costOfTheDish = costOfTheDish;
    }
}


public class Main {

    // Создаем экземпляр класса Scanner.
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        // 1. Запрашиваем количество людей (с проверкой правильности ввода). Получаем целое число N

        int countOfFriends = amountFriends();

        // 2. Запрашиваем наименование заказа и его стоимость до слова завершить. Получаем строку заказов и общую стоимость

        Order order = getOrder();

        // 3. Получаем результат деления общей стоимости заказа на количество людей в компанию

        double partPayment = order.costOfTheDish / countOfFriends;

        // 4. Форматируем результат в рублях и с двумя знаками после запятой

        String printLineCost = payRubles(partPayment);

        // 5. Выводим результат
        System.out.println("_______________________________________");
        System.out.println("Уважаемые посетители нашего заведения!");
        System.out.println("Ваш заказ сегодня: \n" + order.dishInOrder);
        System.out.println("на общую сумму: " + payRubles(order.costOfTheDish));
        System.out.println("Каждый из Вас должен заплатить по: " + printLineCost);
    }


    //Метод вызывается для ввода количества застольных человек,с кем делить счет
    public static int amountFriends() {
        int N;
        while (true) {
            System.out.println("Введите количество человек на сколько делить заказ");

            if(scanner.hasNextInt()) { // если введено целое число - работаем с ним
                N = scanner.nextInt();
                if (N > 1) {  break;  }   //Если введенное значение число и оно больше 1 - прерываем. В противном случаем цикл повторяется до бесконечности
            } else {
                scanner.next();;
            }
            // Проверка введенного значения
        }
        return N;
    }

    // Метод расчета и возврата списка блюд и общей стоимости заказа (Order.название, Order.цена)
    public static Order getOrder() {
        // Задаем начальные значения для возврата при обьявлении переменных
        String dishInOrder = "";
        double costOfTheDish = 0.0;
        String dish = "";
        double cost ;
        Scanner scanner = new Scanner(System.in);

        System.out.println("*************************************************************");
        System.out.println("Введите последовательно название и стоимость заказанных блюд.");
        System.out.println("Справочно: для окончания подсчета введите слово ЧАЕВЫЕ и их стоимость, или ЗАВЕРШИТЬ");
        System.out.println(" ");

        while (!(dish.equalsIgnoreCase("Чаевые")))   // цикл работает пока вместо названия блюда не будет введено "Завершение"  - без учета регистра. При вводе "Чаевые" - !true - прервет цикл
        {
            System.out.println("Введите название блюда (или кодовое слово 'Завершить'): ");
            dish = scanner.next();            // Считываем название
            if (dish.equalsIgnoreCase("Завершить")) {  break; }

            dishInOrder = (dishInOrder + dish + "\n");        // Формируем список блюд

            System.out.println("Введите стоимость блюда (или размер чаевых): ");

            while (true) {

                if (scanner.hasNextDouble() || scanner.hasNextInt() ) { // если введено  число - работаем с ним
                    cost = scanner.nextDouble();                // Считываем цену
                    if (cost > 0)                                    // Проверяем ввод пользователя. Если не число - не суммируем. Можно и переспросить
                    {
                        costOfTheDish = costOfTheDish + cost;       // Суммируем
                        break;
                    }
                } else {

                    System.out.println("Введите, пожалуйста, правильно стоимость указанного блюда, а не это  " + scanner.next()); //выводим в консоль сообщение

                }
            }

        }

        return new Order(dishInOrder, costOfTheDish);
    }

    // Функция (метод) определения окончания рубль - рубля - рублей и форматирования до 2 знаков после запятой
    public static String payRubles(double partPayment) {
        double PayInt;
        PayInt = Math.floor(partPayment);
        String endRub = "рубль";                //Обьявляем переменную для окончания и задем значение по умолчанию
        if (PayInt % 10 == 0 || PayInt % 10 > 4 || (PayInt % 100 > 10 && PayInt % 100 < 20)) {
            endRub = "рублей";
        } else if (PayInt % 10 > 1 && PayInt % 10 < 5) {
            endRub = "рубля";
        }
        return String.format("%.2f", partPayment) + " " + endRub;  //Форматируем и возвращаем строку с окончанием рубль - рубля - рублей
    }
}
