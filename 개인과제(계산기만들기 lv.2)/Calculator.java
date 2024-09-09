import java.util.ArrayList;
import java.util.List;

public class Calculator {
    // 결과를 저장할 컬렉션 (private 접근 제어자)
    private List<Double> results;

    // 생성자
    public Calculator() {
        results = new ArrayList<>();
    }

    // 사칙연산 수행 후 결과를 반환하고 저장하는 메서드
    public double performOperation(double num1, double num2, char operator) {
        double result = 0.0;
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
                    return Double.NaN; // 반환값이 NaN (Not a Number)으로 설정
                }
                break;
            default:
                System.out.println("오류: 잘못된 연산자입니다. '+', '-', '*', '/' 중 하나를 입력하세요.");
                return Double.NaN; // 반환값이 NaN (Not a Number)으로 설정
        }

        // 결과를 저장
        results.add(result);
        return result;
    }

    // 결과 목록 반환 메서드 (getter)
    public List<Double> getResults() {
        return new ArrayList<>(results); // 결과의 복사본을 반환하여 직접 수정 불가
    }

    // 결과 목록 수정 메서드 (setter) - 특정 결과를 수정하는 예제
    public void setResult(int index, double value) {
        if (index >= 0 && index < results.size()) {
            results.set(index, value);
        } else {
            System.out.println("오류: 인덱스가 유효하지 않습니다.");
        }
    }

    // 가장 먼저 저장된 결과 삭제 메서드
    public void removeFirstResult() {
        if (!results.isEmpty()) {
            results.remove(0); // 첫 번째 요소 삭제
        } else {
            System.out.println("결과가 비어있습니다. 삭제할 수 없습니다.");
        }
    }
}
