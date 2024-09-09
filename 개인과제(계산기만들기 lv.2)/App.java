import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class App {
    private static final Set<Character> VALID_OPERATORS = new HashSet<>();

    static {
        VALID_OPERATORS.add('+');
        VALID_OPERATORS.add('-');
        VALID_OPERATORS.add('*');
        VALID_OPERATORS.add('/');
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Calculator calculator = new Calculator(); // Calculator 인스턴스 생성

            boolean exit = false;
            while (!exit) {
                System.out.print("계산을 시작하려면 'start'를 입력하고 종료하려면 'exit'를 입력하세요: ");
                String input = scanner.nextLine().trim();

                switch (input.toLowerCase()) {
                    case "exit":
                        System.out.println("프로그램을 종료합니다.");
                        exit = true;
                        break;
                    case "start":
                        performCalculations(scanner, calculator);
                        break;
                    default:
                        System.out.println("잘못된 입력입니다. 'start' 또는 'exit'를 입력해주세요.");
                        break;
                }
            }
        }
    }

    private static void performCalculations(Scanner scanner, Calculator calculator) {
        boolean resultAvailable = false;

        while (true) {
            try {
                if (!resultAvailable) {
                    System.out.print("첫 번째 숫자를 입력하세요: ");
                    double num1 = getValidDouble(scanner);

                    char operator = getValidOperator(scanner);

                    System.out.print("두 번째 숫자를 입력하세요: ");
                    double num2 = getValidDouble(scanner);

                    // 계산 수행
                    double result = calculator.performOperation(num1, num2, operator);

                    if (Double.isNaN(result)) {
                        System.out.println("오류: 다시 입력하세요.");
                        resultAvailable = false;
                        continue;
                    }

                    System.out.println("계산 결과: " + result);
                    resultAvailable = true;
                }

                System.out.print("계산을 계속하려면 'continue', 새 계산을 하려면 'new', 삭제하려면 'delete', 종료하려면 'exit'를 입력하세요: ");
                String option = scanner.nextLine().trim();

                switch (option.toLowerCase()) {
                    case "continue":
                        if (calculator.getResults().isEmpty()) {
                            System.out.println("계산 결과가 없습니다. 새로운 계산을 하세요.");
                            break;
                        }

                        char modifyOperator = getValidOperator(scanner);

                        System.out.print("추가할 숫자를 입력하세요: ");
                        double modifyNum = getValidDouble(scanner);

                        // 마지막 결과에 추가 연산 수행
                        List<Double> results = calculator.getResults();
                        double lastResult = results.get(results.size() - 1);
                        double updatedResult = calculator.performOperation(lastResult, modifyNum, modifyOperator);

                        if (Double.isNaN(updatedResult)) {
                            System.out.println("오류: 다시 입력하세요.");
                            continue;
                        }

                        System.out.println("계산 결과: " + updatedResult);
                        break;

                    case "new":
                        resultAvailable = false;
                        break;

                    case "delete":
                        if (calculator.getResults().isEmpty()) {
                            System.out.println("삭제할 결과가 없습니다.");
                        } else {
                            calculator.removeFirstResult();
                            System.out.println("가장 먼저 저장된 결과가 삭제되었습니다.");
                        }
                        break;

                    case "exit":
                        System.out.println("계산기를 종료합니다.");
                        return; // 메서드 종료

                    default:
                        System.out.println("잘못된 입력입니다. 'continue', 'new', 'delete', 'exit' 중 하나를 입력해주세요.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("입력 오류가 발생했습니다. 숫자나 연산자를 올바르게 입력했는지 확인하세요.");
                scanner.nextLine();
            }
        }
    }

    private static double getValidDouble(Scanner scanner) {
        double result = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("숫자를 입력하세요: ");
            try {
                result = scanner.nextDouble();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                scanner.next();
            } finally {
                scanner.nextLine();
            }
        }
        return result;
    }

    private static char getValidOperator(Scanner scanner) {
        char operator = ' '; // 초기값으로 공백 문자 할당
        boolean validInput = false;
        while (!validInput) {
            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
            String input = scanner.nextLine().trim();

            if (input.length() == 1) {
                operator = input.charAt(0);
                if (VALID_OPERATORS.contains(operator)) {
                    validInput = true;
                } else {
                    System.out.println("잘못된 연산자입니다. '+', '-', '*', '/' 중 하나를 입력하세요.");
                }
            } else {
                System.out.println("잘못된 입력입니다. 한 개의 문자만 입력해주세요.");
            }
        }
        return operator;
    }
}
