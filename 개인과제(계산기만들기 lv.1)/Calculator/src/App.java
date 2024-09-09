import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("계산을 시작하려면 'start'를 입력하시고 종료하려면 'exit'를 입력하세요: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (input.equalsIgnoreCase("start")) {
                performCalculations(scanner);
            } else {
                System.out.println("잘못된 입력입니다. 'start' 또는 'exit'를 입력해주세요.");
            }
        }

        scanner.close();
    }

    private static void performCalculations(Scanner scanner) {
        double result = 0.0;
        boolean resultAvailable = false;

        while (true) {
            try {
                if (!resultAvailable) {
                    System.out.print("첫번째 숫자를 입력하세요: ");
                    double num1 = scanner.nextDouble();


                    scanner.nextLine();

                    System.out.print("사칙연산 기호를 입력하세요: ");
                    char operator = scanner.nextLine().trim().charAt(0);

                    System.out.print("두번째 숫자를 입력하세요: ");
                    double num2 = scanner.nextDouble();


                    scanner.nextLine();

                    switch (operator) {
                        case '+':
                            result = num1 + num2;
                            break;
                        case '-':
                            result = num1 - num2;
                            break;
                        case '*':
                            result = num1 * num2;
                            break;
                        case '/':
                            if (num2 != 0) {
                                result = num1 / num2;
                            } else {
                                System.out.println("오류: 나눗셈 연산에서 분모(두 번째 숫자)에 0이 입력될 수 없습니다.");
                                resultAvailable = false;
                                continue;
                            }
                            break;
                        default:
                            System.out.println("오류: 잘못된 연산자입니다. '+', '-', '*', '/' 중 하나를 입력하세요.");
                            resultAvailable = false;
                            continue;
                    }

                    resultAvailable = true;
                }


                System.out.println("계산 결과: " + result);
                System.out.print("계산을 계속하려면 'continue', 새 계산을 하려면 'new', 종료하려면 'exit'를 입력하세요: ");
                String option = scanner.nextLine().trim();

                if (option.equalsIgnoreCase("continue")) {

                    System.out.print("추가할 사칙연산 기호를 입력하세요: ");
                    char modifyOperator = scanner.nextLine().trim().charAt(0);

                    System.out.print("추가할 숫자를 입력하세요: ");
                    double modifyNum = scanner.nextDouble();


                    scanner.nextLine();

                    switch (modifyOperator) {
                        case '+':
                            result += modifyNum;
                            break;
                        case '-':
                            result -= modifyNum;
                            break;
                        case '*':
                            result *= modifyNum;
                            break;
                        case '/':
                            if (modifyNum != 0) {
                                result /= modifyNum;
                            } else {
                                System.out.println("오류: 나눗셈 연산에서 분모에 0이 입력될 수 없습니다.");
                                continue;
                            }
                            break;
                        default:
                            System.out.println("오류: 잘못된 연산자입니다. '+', '-', '*', '/' 중 하나를 입력하세요.");
                            continue;
                    }
                } else if (option.equalsIgnoreCase("new")) {

                    resultAvailable = false;
                } else if (option.equalsIgnoreCase("exit")) {
                    System.out.println("계산기를 종료합니다.");
                    break;
                } else {
                    System.out.println("잘못된 입력입니다. 'continue', 'new', 'exit' 중 하나를 입력해주세요.");
                }

            } catch (Exception e) {
                System.out.println("입력 오류가 발생했습니다. 숫자나 연산자를 올바르게 입력했는지 확인하세요.");
                scanner.nextLine();
            }
        }
    }
}