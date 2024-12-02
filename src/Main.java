import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;


public class Main {
    public static <N extends Number> void fillList(List<? super N> list) {
        if (list == null) {
            throw new IllegalArgumentException("Список не может быть null.");
        }
        list.clear();
        for (int i = 1; i <= 100; i++) {
            list.add((N) Integer.valueOf(i));
        }
    }
    public static <T, P> List<P> universal(List<T> inputList, Function<T, P> f) {
        List<P> resultList = new ArrayList<>();
        for (T element : inputList) {
            resultList.add(f.apply(element));
        }
        return resultList;
    }
    public static <T> List<T> filter(List<T> list, Predicate<T> predict) {
        List<T> filteredList = new ArrayList<>();
        for (T i : list) {
            if (predict.test(i)) {
                filteredList.add(i );
            }
        }
        return filteredList;
    }
    public static <T, R> R reduce(List<T> list, Reducer<R, T> reducer, R initialValue) {
        R result = initialValue;
        for (T element : list) {
            result = reducer.reduce(result, element);
        }
        return result;
    }
    public interface Reducer<R, T> {
        R reduce(R celoe, T tek);
    }
    public static <T, P> P collection(List<T> list, Function<List<T>, P> collectionCreator, Function<List<T>, P> listProcessor) {
        return listProcessor.apply(list);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //1.3
        int number1 = 0;
        while (true) {
            try {
                System.out.print("Введите 1 число: ");
                number1 = Integer.parseInt(in.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }

        ComparableNumber comparable = new ComparableNumber(number1);

        int number2 = 0;
        while (true) {
            try {
                System.out.print("Введите 2 число:  ");
                number2 = Integer.parseInt(in.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }

        int result = comparable.compare(number2);

        if (result > 0) {
            System.out.println("1 больше 2");
        } else if (result < 0) {
            System.out.println("1 меньше 2");
        } else {
            System.out.println("равны");
        }

        //1.6
        Massiv<Integer> massiv = null;

        System.out.println("Создание обобщённого массива:");
        System.out.println("1 - Инициализировать массивом чисел");
        System.out.println("2 - Инициализировать перечнем чисел через запятую");
        System.out.println("3 - Инициализировать другим списком");
        System.out.print("Выберите метод инициализации: ");

        int choice = in.nextInt();
        in.nextLine();

        try {
            switch (choice) {
                case 1 -> {
                    System.out.print("Введите числа через пробел: ");
                    String[] tokens = in.nextLine().split(" ");
                    Integer[] values = Arrays.stream(tokens).map(Integer::parseInt).toArray(Integer[]::new);
                    massiv = new Massiv<>(values);
                }
                case 2 -> {
                    System.out.print("Введите числа через запятую: ");
                    String[] tokens = in.nextLine().split(",");
                    Integer[] values = Arrays.stream(tokens).map(Integer::parseInt).toArray(Integer[]::new);
                    massiv = new Massiv<>(values);
                }
                case 3 -> {
                    System.out.print("Введите числа через пробел: ");
                    String[] tokens = in.nextLine().split(" ");
                    List<Integer> values = new ArrayList<>();
                    for (String token : tokens) {
                        values.add(Integer.parseInt(token));
                    }
                    massiv = new Massiv<>(values);
                }
                default -> System.out.println("Неверный выбор.");
            }

            if (massiv != null) {
                while (true) {
                    System.out.println("\nВыберите действие:");
                    System.out.println("1 - Получить значение по индексу");
                    System.out.println("2 - Заменить значение по индексу");
                    System.out.println("3 - Проверить, пуст ли массив");
                    System.out.println("4 - Узнать размер массива");
                    System.out.println("5 - Показать все значения");
                    System.out.println("6 - Показать значения как массив");
                    System.out.println("0 - Выйти");
                    System.out.print("Ваш выбор: ");

                    int action = in.nextInt();
                    if (action == 0) break;

                    switch (action) {
                        case 1 -> {
                            System.out.print("Введите индекс: ");
                            int index = in.nextInt();
                            try {
                                System.out.println("Значение: " + massiv.get(index));
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Ошибка: " + e.getMessage());
                            }
                        }
                        case 2 -> {
                            System.out.print("Введите индекс: ");
                            int index = in.nextInt();
                            System.out.print("Введите новое значение: ");
                            in.nextLine();
                            int newValue = in.nextInt();
                            try {
                                massiv = massiv.set(index, newValue);
                                System.out.println("Значение обновлено.");
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Ошибка: " + e.getMessage());
                            }
                        }
                        case 3 -> System.out.println("Массив " + (massiv.isEmpty() ? "пуст." : "не пуст."));
                        case 4 -> System.out.println("Размер массива: " + massiv.size());
                        case 5 -> System.out.println("Значения массива: " + massiv.toString());
                        case 6 -> {
                            Object[] array = massiv.toArray();
                            System.out.print("Массив значений: ");
                            for (Object obj : array) {
                                System.out.print(obj + " ");
                            }
                            System.out.println();
                        }
                        default -> System.out.println("Неверный выбор.");
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода! Введите корректное значение.");
        }
        //2.4
        try {
            System.out.println("Выберите тип списка:");
            System.out.println("1 - Список чисел (List<Number>)");
            System.out.println("2 - Список объектов (List<Object>)");
            System.out.println("3 - Список типа Integer");
            System.out.print("Ваш выбор: ");
            int choice1 = in.nextInt();
            in.nextLine();

            List<?> rawList = null;
            switch (choice1) {
                case 1 -> rawList = new ArrayList<Number>();
                case 2 -> rawList = new ArrayList<Object>();
                case 3 -> rawList = new ArrayList<Integer>();
                default -> {
                    System.out.println("Неверный выбор. Программа завершена.");
                    return;
                }
            }


            if (rawList instanceof List<?> list) { //Проверяет, является ли объект rawList экземпляром интерфейса List<?>
                @SuppressWarnings("unchecked")
                List<? super Number> genericList = (List<? super Number>) list;

                System.out.println("Заполняем список числами от 1 до 100...");
                fillList(genericList);


                System.out.println("Список успешно заполнен. Выберите действие:");
                while (true) {
                    System.out.println("1 - Показать весь список");
                    System.out.println("2 - Узнать размер списка");
                    System.out.println("3 - Очистить список");
                    System.out.println("4 - Заполнить список заново");
                    System.out.println("0 - Выйти");
                    System.out.print("Ваш выбор: ");

                    int action = in.nextInt();
                    switch (action) {
                        case 1 -> System.out.println("Список: " + genericList);
                        case 2 -> System.out.println("Размер списка: " + genericList.size());
                        case 3 -> {
                            genericList.clear();
                            System.out.println("Список очищен.");
                        }
                        case 4 -> fillList(genericList);
                        case 0 -> {
                            System.out.println("Программа завершена.");
                            return;
                        }
                        default -> System.out.println("Неверный выбор. Попробуйте снова.");
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода! Введите корректное значение.");
        } catch (ClassCastException e) {
            System.out.println("Ошибка приведения типов: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Неожиданная ошибка: " + e.getMessage());
        }
        //3.1
        System.out.println("Выберите действие:");
        System.out.println("1 - Преобразовать строки в их длины");
        System.out.println("2 - Сделать отрицательные числа положительными");
        System.out.println("3 - Найти максимальные элементы массивов");
        System.out.print("Ваш выбор: ");

        try {
            int choice2 = in.nextInt();
            in.nextLine();

            switch (choice2) {
                case 1 -> {
                    List<String> stringList = Arrays.asList("qwerty", "asdfg", "zx");
                    System.out.println("Исходный список строк: " + stringList);

                    List<Integer> lengths = universal(stringList, String::length);
                    System.out.println("Длины строк: " + lengths);
                }
                case 2 -> {
                    List<Integer> numbers = Arrays.asList(1, -3, 7);
                    System.out.println("Исходный список чисел: " + numbers);

                    List<Integer> positives = universal(numbers, Math::abs);
                    System.out.println("Положительные числа: " + positives);
                }
                case 3 -> {
                    List<int[]> arrays = List.of(
                            new int[]{1, 2, 5},
                            new int[]{-5, 5, 5},
                            new int[]{10, 20, 39}
                    );
                    System.out.println("Исходные массивы: ");
                    arrays.forEach(array -> System.out.println(Arrays.toString(array)));

                    List<Integer> maxValues = universal(arrays, array -> Arrays.stream(array).max().orElseThrow());
                    System.out.println("Максимальные значения: " + maxValues);
                }
                default -> System.out.println("Неверный выбор.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода! Введите корректное значение.");
        }
        //3.2
        System.out.println("Выберите пример для тестирования:");
        System.out.println("1 - Фильтрация строк длиной меньше 3 символов");
        System.out.println("2 - Фильтрация положительных чисел");
        System.out.println("3 - Фильтрация массивов с положительными элементами");


        int choice3 = in.nextInt();

        switch (choice3) {
            case 1 -> {
                List<String> stringList = Arrays.asList("qwerty", "asdfg", "zx");
                List<String> filteredStrings = filter(stringList, str -> str.length() >= 3);
                System.out.println("Исходный список строк: " + stringList);
                System.out.println("Отфильтрованный список: " + filteredStrings);
            }
            case 2 -> {
                // Пример 2: числа -> удаляем положительные
                List<Integer> numbers = Arrays.asList(1, -3, 7);
                List<Integer> filteredNumbers = filter(numbers, num -> num <= 0);
                System.out.println("Исходный список чисел: " + numbers);
                System.out.println("Отфильтрованный список: " + filteredNumbers);
            }
            case 3 -> {
                List<int[]> arrays = List.of(
                        new int[]{1, 2, 3},
                        new int[]{-5, -121, -10},
                        new int[]{10, -2, -1},
                        new int[]{130, -220, -32}
                );
                List<int[]> filteredArrays = filter(arrays, array -> Arrays.stream(array).allMatch(num -> num <= 0));
                System.out.println("Исходный список массивов: " + Arrays.deepToString(arrays.toArray()));
                System.out.println("Отфильтрованный список: " + Arrays.deepToString(filteredArrays.toArray()));
            }
            default -> System.out.println("Неверный выбор!");
        }
        //3.3
        List<String> strings = List.of("qwerty", "asdfg", "zx");
        String qq = reduce(strings, (a, b) -> a + b, "");
        System.out.println("Объединённая строка: " + qq);


        List<Integer> numbers = List.of(1, -3, 7);
        int sum = reduce(numbers, Integer::sum, 0);
        System.out.println("Сумма чисел: " + sum);


        List<List<Integer>> Lists = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(12,-6)
        );
        int totalSize = reduce(Lists, (a, b) -> a + b.size(), 0);
        System.out.println("Общее количество элементов: " + totalSize);
        //3.4
        List<Integer> numbers1 = List.of(1, -3, 7);
        Map<Boolean, List<Integer>> partitionedNumbers = collection(numbers1,
                (list) -> new HashMap<>(),
                (list) -> list.stream().collect(Collectors.partitioningBy(num -> num > 0))
        );
        System.out.println("Положительные и отрицательные числа: " + partitionedNumbers);

        List<String> strings1 = List.of("qwerty", "asdfg", "zx", "qw");
        Map<Integer, List<String>> groupedByLength = collection(strings1,
                (list) -> new HashMap<>(),
                (list) -> list.stream().collect(Collectors.groupingBy(String::length))
        );
        System.out.println("Группировка строк по длине: " + groupedByLength);
        
        List<String> uniqueStrings = List.of("qwerty", "asdfg", "qwerty", "qw");
        Set<String> uniqueSet = collection(uniqueStrings,
                (list) -> new HashSet<>(),
                (list) -> new HashSet<>(list)
        );
        System.out.println("Уникальные строки: " + uniqueSet);

    }
}