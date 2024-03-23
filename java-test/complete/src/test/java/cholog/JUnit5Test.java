package cholog;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit5는 자바 언어를 사용하는 소프트웨어 개발자들을 위한 테스트 프레임워크 중 하나입니다.
 * 이는 자바 언어로 작성된 소프트웨어의 품질을 향상시키기 위해 사용되며, 주로 단위 테스트를 작성하고 실행하는 데에 쓰입니다.
 * 해당 테스트 또한 JUnit5를 사용하여 작성되었습니다.
 * <p>
 * 참고: <a href="https://junit.org/junit5/docs/current/user-guide/">JUnit5 User Guide</a>
 */
@DisplayName("JUnit5 학습 테스트")
public class JUnit5Test {
    /**
     * `@Test` 애노테이션은 해당 메서드가 테스트 메서드임을 나타냅니다.
     * 테스트 메서드는 `void` 타입을 반환하며, `@Test` 애노테이션을 사용하여 테스트 메서드임을 나타냅니다.
     * 테스트 메서드는 테스트를 위해 사용되기 때문에, 테스트 메서드의 이름은 테스트의 의도를 나타내는 이름으로 작성하는 것이 좋습니다.
     */
    @Nested
    @DisplayName("@Test 애노테이션 학습 테스트")
    class TestAnnotationTest {
        /**
         * `@Test` 애노테이션이 없다면 해당 메서드는 테스트 메서드가 아닙니다.
         * 따라서 해당 메서드는 테스트 메서드가 아니기 때문에, 테스트 메서드로서의 역할을 수행하지 않습니다.
         * `@Test` 애노테이션을 사용하면 해당 메서드는 테스트 메서드가 되며, 테스트 메서드로서의 역할을 수행합니다.
         */
        @Test
        void Test_애노테이션을_붙여_테스트_메서드로_만든다() {
        }

        /**
         * return 타입이 `void`가 아니라면 해당 메서드는 테스트 메서드가 아닙니다.
         * 따라서 해당 메서드는 테스트 메서드가 아니기 때문에, 테스트 메서드로서의 역할을 수행하지 않습니다.
         */
        @Test
        void return_타입이_void가_아니라면_테스트_메서드가_아니다() {
        }
    }

    /**
     * `@DisplayName` 애노테이션은 해당 테스트의 이름을 나타냅니다.
     * 테스트의 메서드에 한글을 작성해도 동작은 하지만 크게 3가지 문제가 있습니다.
     * <p>
     * 1. 도구 및 라이브러리와의 호환성: 일부 개발 도구, 라이브러리 및 프레임워크는 한글을 지원하지 않습니다.
     * 2. 언어 혼합: 언어 식별자가 혼합된 코드베이스에서는 일관성을 유지하는 것이 어려울 수 있습니다. 코드의 일부에서는 영어 명명을 사용하고 다른 부분에서는 한글을 사용하면 전체 코드 가독성이 떨어질 수 있습니다.
     * 3. 경고 발생: 일부 IDE는 한글을 사용할 때 경고를 발생시킵니다. 무의미한 경고가 많다면 중요한 경고를 놓칠 수 있습니다.
     * <p>
     * 따라서 테스트의 이름을 한글로 작성하고 싶다면 `@DisplayName` 애노테이션을 사용하는 것이 좋습니다.
     */
    @Nested
    @DisplayName("@DisplayName 애노테이션 학습 테스트")
    class DisplayNameAnnotationTest {
        /**
         * `@DisplayName` 애노테이션이 없다면 해당 메서드명에 `Non-ASCII characters` 경고가 발생합니다.
         * 따라서 해당 경고를 제거하기 위해 `@DisplayName` 애노테이션을 사용하고 메서드명은 영어로 작성하는 것이 좋습니다.
         * <p>
         * ps. 다만 학습 테스트에서는 의미 전달을 위해 한글 메서드명을 사용합니다.
         */
        @Test
        @DisplayName("DisplayName_애노테이션을_붙여_경고를_제거한다")
        void use_displayName() {
        }
    }

    /**
     * `@Nested` 애노테이션은 해당 클래스가 중첩 클래스임을 나타냅니다.
     * 중첩 클래스는 클래스 내부에 선언된 클래스를 의미합니다.
     * 중첩으로 표현하는 이유는 클래스의 의미를 명확하게 하기 위함입니다.
     */
    @Nested
    @DisplayName("@Nested 애노테이션 학습 테스트")
    class NestedAnnotationTest {
        /**
         * `@Nested` 애노테이션이 없다면 해당 메서드는 중첩 클래스가 아닙니다.
         * 따라서 해당 메서드는 중첩 클래스 내부에 있는게 아니기 때문에, 테스트 메서드로서의 역할을 수행하지 않습니다.
         * `@Nested` 애노테이션을 사용하면 해당 메서드는 중첩 클래스 내부에 있는 것으로 인식되며, 테스트 메서드로서의 역할을 수행합니다.
         */
        @Test
        @DisplayName("@Nested 애노테이션을 붙여줘야 중첩 클래스로서의 역할을 수행한다")
        void Nested_애노테이션을_붙여줘야_중첩_클래스로서의_역할을_수행한다() {
        }
    }

    /**
     * `@Disabled` 애노테이션은 해당 테스트를 비활성화합니다.
     * 테스트를 비활성화한다는 것은 해당 테스트를 실행하지 않겠다는 의미입니다.
     * <p>
     * `@Disabled` 애노테이션은 `@Test` 애노테이션과 함께 사용됩니다.
     * `@Test` 애노테이션은 해당 메서드가 테스트 메서드임을 나타내며, `@Disabled` 애노테이션은 해당 테스트를 비활성화한다는 것을 나타냅니다.
     */
    @Disabled
    @Nested
    @DisplayName("@Disabled 애노테이션 학습 테스트")
    class DisabledAnnotationTest {
        /**
         * `@Disabled` 애노테이션이 없다면 해당 메서드는 테스트 메서드입니다.
         * 따라서 해당 메서드는 테스트 메서드로서의 역할을 수행합니다.
         * `@Disabled` 애노테이션을 사용하면 해당 메서드는 테스트 메서드가 아니기 때문에, 테스트 메서드로서의 역할을 수행하지 않습니다.
         */
        @Disabled
        @Test
        @DisplayName("@Disabled 애노테이션을 붙여줘야 테스트 메서드로서의 역할을 수행하지 않는다")
        void Disabled_애노테이션을_붙여줘야_테스트_메서드로서의_역할을_수행하지_않는다() {
            throw new RuntimeException("항상 실패한다.");
        }

        /**
         * `@Disabled` 애노테이션은 클래스에도 적용 가능하며, 클래스에 `@Disabled` 애노테이션을 붙이면 해당 클래스의 모든 테스트가 비활성화됩니다.
         * 따라서 해당 클래스의 모든 테스트가 비활성화되기 때문에, 테스트 메서드로서의 역할을 수행하지 않습니다.
         */
        @Test
        @DisplayName("항상 실패하는 테스트 1")
        void 항상_실패하는_테스트_1() {
            throw new RuntimeException("항상 실패한다.");
        }

        @Test
        @DisplayName("항상 실패하는 테스트 2")
        void 항상_실패하는_테스트_2() {
            throw new RuntimeException("항상 실패한다.");
        }

        @Test
        @DisplayName("항상 실패하는 테스트 3")
        void 항상_실패하는_테스트_3() {
            throw new RuntimeException("항상 실패한다.");
        }

        @Test
        @DisplayName("항상 실패하는 테스트 3")
        void 항상_실패하는_테스트_4() {
            throw new RuntimeException("항상 실패한다.");
        }
    }

    /**
     * `assertEquals` 메서드는 두 값이 같은지 비교합니다.
     * 두 값이 같다면 테스트는 성공하며, 두 값이 다르다면 테스트는 실패합니다.
     * <p>
     * `assertEquals` 메서드는 `org.junit.jupiter.api.Assertions` 클래스에 정의되어 있습니다.
     * 따라서 `Assertions` 클래스를 `static`으로 임포트하여 사용하는 것이 좋습니다.
     */
    @Nested
    @DisplayName("assertEquals 메서드 학습 테스트")
    class AssertEqualsMethodTest {
        /**
         * `assertEquals` 메서드는 `assertEquals(expected, actual)` 형태로 오버로딩되어 있습니다.
         * `expected`는 예상되는 값이며, `actual`은 실제 값입니다.
         * <p>
         * `assertEquals` 메서드는 두 값이 같다면 테스트를 성공시킵니다.
         * 기존에 작성된 검증 코드를 `assertEquals` 메서드를 사용하도록 변경해주세요.
         */
        @Test
        @DisplayName("assertEquals 메서드로 두 값이 같은지 비교한다")
        void assertEquals_메서드로_두_값이_같은지_비교한다() {
            final var a = 1;
            final var b = 2;
            final var actual = a + b;

            final var expected = 3;

            assertEquals(expected, actual);
        }

        /**
         * `assertEquals` 메서드는 `expected`와 `actual`이 같은지 비교하기 위해 `equals` 메서드를 사용합니다.
         * 따라서 `expected`와 `actual`의 타입이 `equals` 메서드를 정의하고 있다면 `assertEquals` 메서드를 사용할 수 있습니다.
         * <p>
         * 그 말은 `equals` 메서드를 정의하지 않은 타입은 `assertEquals` 메서드를 사용할 수 없다는 것을 의미합니다.
         * 기존 객체 비교 코드에서 `equals`를 재정의하여 테스트를 성공시켜주세요.
         */
        @Test
        @DisplayName("assertEquals 메서드로 두 객체가 같은지 비교한다")
        void assertEquals_메서드로_두_객체가_같은지_비교한다() {
            class LocalObject {
                private final int value;

                public LocalObject(int value) {
                    this.value = value;
                }

                @Override
                public boolean equals(Object o) {
                    if (this == o) return true;
                    if (o == null || getClass() != o.getClass()) return false;
                    LocalObject that = (LocalObject) o;
                    return value == that.value;
                }

                @Override
                public int hashCode() {
                    return Objects.hash(value);
                }
            }

            final var a = new LocalObject(1);
            final var b = new LocalObject(1);

            assertEquals(a, b);
        }

        /**
         * `assertEquals(expected, actual, message)`는 테스트가 실패했을 때 출력되는 메시지를 지정할 수 있습니다.
         * `message`는 테스트가 실패했을 때 출력되는 메시지입니다.
         * 기존에 작성된 검증 코드를 `assertEquals(expected, actual, message)` 형태로 변경해주세요.
         */
        @Test
        @DisplayName("assertEquals 메서드로 두 값이 같은지 비교하며, 테스트가 실패했을 때 출력되는 메시지를 지정한다")
        void assertEquals_메서드로_두_값이_같은지_비교하며_테스트가_실패했을_때_출력되는_메시지를_지정한다() {
            final var a = 1;
            final var b = 2;
            final var actual = a + b;

            final var expected = 3;

            assertEquals(expected, actual, "두 값이 일치하지 않습니다.");
        }

        /**
         * `assertNotEquals` 메서드는 두 값이 다르다면 테스트를 성공시킵니다.
         * 기존에 작성된 검증 코드를 `assertNotEquals` 메서드를 사용하도록 변경해주세요.
         */
        @Test
        @DisplayName("assertNotEquals 메서드로 두 값이 다른지 비교한다")
        void assertNotEquals_메서드로_두_값이_다른지_비교한다() {
            final var a = 1;
            final var b = 2;
            final var actual = a + b;

            final var unexpected = 0;

            assertNotEquals(unexpected, actual);
        }
    }

    /**
     * `assertSame` 메서드는 두 객체가 같은 객체인지 비교합니다.
     * 두 객체가 같은 객체라면 테스트는 성공하며, 두 객체가 다른 객체라면 테스트는 실패합니다.
     */
    @Nested
    @DisplayName("assertSame 메서드 학습 테스트")
    class AssertSameMethodTest {
        /**
         * `assertSame` 메서드는 `assertSame(expected, actual)` 형태로 오버로딩되어 있습니다.
         * `expected`는 예상되는 객체이며, `actual`은 실제 객체입니다.
         * <p>
         * `assertSame` 메서드는 두 객체가 같은 객체라면 테스트를 성공시킵니다.
         * 같은 객체라는 것은 두 객체가 같은 메모리 주소를 가리키고 있다는 것을 의미합니다.
         * 기존에 작성된 검증 코드를 `assertSame` 메서드를 사용하도록 변경해주세요.
         */
        @Test
        @DisplayName("assertSame 메서드로 두 객체가 같은지 비교한다")
        void assertSame_메서드로_두_객체가_같은지_비교한다() {
            final var object = new Object();

            final var actual = object;
            final var expected = object;

            assertSame(expected, actual);
        }
    }

    /**
     * `assertThrows` 메서드는 특정 예외가 발생하는지 비교합니다.
     * 특정 예외가 발생한다면 테스트는 성공하며, 특정 예외가 발생하지 않는다면 테스트는 실패합니다.
     */
    @Nested
    @DisplayName("assertThrows 메서드 학습 테스트")
    class AssertThrowsMethodTest {
        /**
         * `assertThrows` 메서드는 `assertThrows(expectedType, executable)` 형태로 오버로딩되어 있습니다.
         * `assertThrows` 메서드는 특정 예외가 발생한다면 테스트를 성공시킵니다.
         */
        @Test
        @DisplayName("assertThrows 메서드로 특정 예외가 발생하는지 비교한다")
        void assertThrows_메서드로_특정_예외가_발생하는지_비교한다() {
            assertThrows(Exception.class, this::causeException);
        }

        /**
         * `assertThrows` 메서드는 `assertThrows(expectedType, executable)` 형태로 오버로딩되어 있습니다.
         * `expectedType`은 예상되는 예외 타입이며, `executable`은 예외가 발생할 코드입니다.
         */
        @Test
        @DisplayName("assertThrows 메서드로 특정 예외가 발생하는지 비교하며, 특정 예외가 발생하지 않는다면 테스트가 실패한다")
        void assertThrows_메서드로_특정_예외가_발생하는지_비교하며_특정_예외가_발생하지_않는다면_테스트가_실패한다() {
            assertThrows(IllegalCallerException.class, this::causeException);


            /* ----- 아래는 추가로 학습할 분만 보세요! -----
            `executable`은 `@FunctionalInterface`를 사용하여 람다식으로 작성할 수 있습니다.

            `@FunctionalInterface`는 함수형 인터페이스임을 나타내는 애노테이션입니다.
            함수형 인터페이스는 하나의 추상 메서드만을 가지는 인터페이스입니다.
            따라서 함수형 인터페이스는 람다식으로 표현할 수 있습니다.
            또한 람다식의 경우 메서드 참조로 변경할 수 있습니다.

            참고: https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html
            참고: https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
            참고: https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
             */
        }

        private void causeException() {
            throw new IllegalCallerException("예외가 발생했습니다.");
        }
    }

    /**
     * `assertDoesNotThrow` 메서드는 특정 예외가 발생하지 않는지 비교합니다.
     * 특정 예외가 발생하지 않는다면 테스트는 성공하며, 특정 예외가 발생한다면 테스트는 실패합니다.
     */
    @Nested
    @DisplayName("assertDoesNotThrow 메서드 학습 테스트")
    class AssertDoesNotThrowMethodTest {
        /**
         * `assertDoesNotThrow` 메서드는 `assertDoesNotThrow(executable)` 형태로 오버로딩되어 있습니다.
         * `assertDoesNotThrow` 메서드는 특정 예외가 발생하지 않는다면 테스트를 성공시킵니다.
         * <p>
         * `assertDoesNotThrow` 메서드를 사용하지 않아도 테스트는 성공하지만, `assertDoesNotThrow` 메서드를 사용하지 않는다면 테스트의 의도를 명확하게 표현할 수 없습니다.
         * 따라서 `assertDoesNotThrow` 메서드를 사용하여 테스트의 의도를 명확하게 표현하는 것이 좋습니다.
         */
        @Test
        @DisplayName("assertDoesNotThrow 메서드로 특정 예외가 발생하지 않는 것을 명시한다")
        void assertDoesNotThrow_메서드로_특정_예외가_발생하지_않는_것을_명시한다() {
            assertDoesNotThrow(() -> {
                final var number = Integer.valueOf(0x80000000);
            });
        }
    }

    /**
     * `assertAll` 메서드는 여러 검증 코드를 한 번에 실행합니다.
     * `assertAll` 메서드는 `assertAll(executables)` 형태로 오버로딩되어 있습니다.
     * `executables`은 검증 코드입니다.
     */
    @Nested
    @DisplayName("assertAll 메서드 학습 테스트")
    class AssertAllMethodTest {
        /**
         * `assertAll` 메서드는 `assertAll(executables)` 형태로 오버로딩되어 있습니다.
         * `assertAll` 메서드는 `executables`에 포함된 검증 코드를 모두 실행합니다.
         * `executables`에 포함된 검증 코드 중 하나라도 실패한다면 테스트는 실패합니다.
         * <p>
         * `assertAll`를 사용하는 이유는 여러 검증 코드가 존재할 때 문제가 발생한다면 어떤 검증 코드에서 문제가 발생했는지 알기 어렵기 때문입니다.
         */
        @Test
        @DisplayName("assertAll 메서드로 여러 검증 코드를 한 번에 실행한다")
        void assertAll_메서드로_여러_검증_코드를_한_번에_실행한다() {
            assertAll(
                    () -> assertEquals(3, 1 + 2),
                    () -> assertEquals(5, 2 + 3),
                    () -> assertEquals(21, 7 * 3),
                    () -> assertEquals(16, 7 * 3 ^ 5),
                    () -> assertEquals(5, 7 * 3 / 5 + 33 / 21),
                    () -> assertEquals(22, 33 * 3 / 5 + 7 / 2),
                    () -> assertEquals(23, 33 * 3 / 5 + 7 / 2 + 1)
            );

        }
    }

    /**
     * `ParameterizedTest` 애노테이션은 테스트에 필요한 파라미터를 제공하는 테스트입니다.
     * 보통 테스트에 따라 `@ValueSource`와 `@MethodSource` 같은 애노테이션 중 적합한 애노테이션을 선택하여 테스트를 진행할 수 있습니다.
     * 이 외에도 `@CsvSource`, `@EnumSource` 등과 같은 다양한 애노테이션을 함께 활용하여 파라미터 값을 주입받을 수 있습니다.
     * <p>
     * `ParameterizedTest`를 사용하면 하나의 테스트 로직 내에서 여러 변수를 선언하지 않고, 주입받는 값을 활용하여 유연하고 단편화된 테스트 코드를 작성할 수 있습니다.
     */
    @Nested
    @DisplayName("@ParameterizedTest 애노테이션 학습 테스트")
    class ParameterizedAnnotationTest {

        /**
         * `@ValueSource` 애노테이션은 `@ParameterizedTest` 애노테이션과 함께 사용되며 정의된 값을 하나의 인자로 받아들이는 역할을 합니다.
         * `@ValueSource` 내부에 선언된 `ints` 속성은 정수 형태의 인자를 입력할 수 있도록 만들어줍니다.
         */
        @ParameterizedTest
        @ValueSource(ints = 1)
        @DisplayName("ValueSource 애노테이션을 붙여 정수 매개변수를 한 번 입력받는다")
        void ValueSource_애노테이션을_붙여_정수_매개변수를_한_번_입력받는다(int value) {
        }

        /**
         * `@ValueSource` 애노테이션의 속성들은 다음과 같이 배열 형태로 입력이 가능합니다.
         * `@ValueSource` 애노테이션의 속성을 배열 형태로 입력해줌으로서, 각 배열의 값마다 각각의 테스트를 수행하도록 구현할 수 있습니다.
         */
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4})
        @DisplayName("ValueSource 애노테이션을 붙여 정수 매개변수를 여러 번 입력받는다")
        void ValueSource_애노테이션을_붙여_정수_매개변수를_여러_번_입력받는다(int value) {
            assertTrue(value > 0 && value < 10);
        }

        /**
         * `@ValueSource` 애노테이션은 `ints`와 같은 속성을 통해 정수 형태를 포함한 string, char, long 등의 다양한 타입을 지원합니다.
         */
        @ParameterizedTest
        @ValueSource(strings = {"a", "b", "c"})
        @DisplayName("ValueSource 애노테이션을 붙여 문자열 매개변수를 여러 번 입력받는다")
        void ValueSource_애노테이션을_붙여_문자열_매개변수를_여러_번_입력받는다(String value) {
            assertEquals(value.length(), 1);
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "2", "3", "4", "5"})
        @DisplayName("ValueSource 애노테이션을 활용하여 1부터 5까지의 문자열 값을 Integer로 parseInt하는 로직이 예외를 발생시키지 않는 지 검증한다")
        void ValueSource_애노테이션을_활용하여_1부터_5까지의_문자열_값을_Integer로_변경하는_로직이_예외를_발생시키지_않는_지_검증한다(String value) {
            assertDoesNotThrow(() -> Integer.parseInt(value));
        }

        /**
         * `@MethodSource` 애노테이션은 메서드 이름을 인자로 받아들이며, 인자에서 호출된 메서드는 테스트 메서드에 전달될 인자를 반환합니다.
         */
        @ParameterizedTest
        @MethodSource("methodSourceTestArguments")
        @DisplayName("MethodSource 애노테이션을 붙여 Object 매개변수를 한 번 입력받는다")
        void MethodSource_애노테이션을_붙여_Object_매개변수를_한_번_입력받는다(Object value) {
        }


        /**
         * `@MethodSource`의 속성으로 사용된 해당 메서드는 `Arguments`를 `Stream`에 담아 return 하여, 각각의 `Arguments`가 테스트에 하나씩 전달되도록 구현된 형태입니다.
         * `Arguments`란 `ParameterizedTest` 에서 사용되는 값의 하나를 가리키며, `JUnit`이 제공하는 객체입니다.
         * <p>
         * `@MethodSource`의 속성으로 사용되는 메서드는 `Stream<?>` 외에도 `Iterator<?>`, `Iterable<?>` 또는 `Object[]` 타입을 반환할 수 있습니다.
         */
        private static Stream<Arguments> methodSourceTestArguments() {
            return Stream.of(
                    Arguments.arguments(new Object())
            );
        }

        @ParameterizedTest
        @MethodSource("methodSourcesTestArguments")
        @DisplayName("MethodSource 애노테이션을 붙여 Object 매개변수를 여러 번 입력받는다")
        void MethodSource_애노테이션을_붙여_Object_매개변수를_여러_번_입력받는다(Object object) {
            assertInstanceOf(Object.class, object);
        }

        /**
         * `Arguments`를 `Stream` 내부에 여러 개 선언해줌으로서, 각 `Arguments`마다 각각의 테스트를 수행하도록 구현할 수 있습니다.
         */
        private static Stream<Arguments> methodSourcesTestArguments() {
            return Stream.of(
                    Arguments.arguments(new Object()),
                    Arguments.arguments(new Object()),
                    Arguments.arguments(new Object()),
                    Arguments.arguments(new Object())
            );
        }

        /**
         * `@MethodSource` 애노테이션을 사용하면 `Iterable` 또한 테스트의 인자로 입력받을 수 있으며, 이 외에 어떠한 객체라도 입력받을 수 있습니다.
         * `Arguments`의 `arguments` 메서드는 매개변수로 `Object... arguments`를 입력받고, 내부 구현을 통해 `Arguments` 객체를 생성하기 때문에 어떠한 타입이든 테스트의 인자로 사용할 수 있는 것입니다.
         */
        @ParameterizedTest
        @MethodSource("methodSourceIterableTestArguments")
        @DisplayName("MethodSource 애노테이션을 붙여 Iterable 매개변수를 입력받는다")
        void MethodSource_애노테이션을_붙여_Iterable_매개변수를_입력받는다(List<Integer> values) {
            assertEquals(values.size(), 3);
        }

        private static Stream<Arguments> methodSourceIterableTestArguments() {
            return Stream.of(
                    Arguments.arguments(
                            List.of(1, 4, 5),
                            List.of(1, 2, 3),
                            List.of(1, 3, 4)
                    )
            );
        }

        /**
         * `@MethodSource` 애노테이션과 `Arguments` 객체를 사용하면 서로 다른 자료형의 값 또한 테스트의 인자로 함께 입력받을 수 있습니다.
         */
        @ParameterizedTest
        @MethodSource("methodSourcesStringAndIntegerTestArguments")
        @DisplayName("MethodSource 애노테이션을 붙여 정수와 문자열 매개변수를 입력받는다")
        void MethodSource_애노테이션을_붙여_정수와_문자열_매개변수를_입력받는다(String v1, int v2) {
            // TODO: `MethodSource`를 사용하지 않고 문자열과 정수 변수들을 직접 선언하여 테스트하는 방식과, `MethodSource`를 통해 입력받아 테스트하는 방식의 차이를 비교해보세요.
            assertEquals(Integer.parseInt(v1), v2);
        }

        private static Stream<Arguments> methodSourcesStringAndIntegerTestArguments() {
            return Stream.of(
                    Arguments.arguments("1", 1),
                    Arguments.arguments("2", 2),
                    Arguments.arguments("3", 3),
                    Arguments.arguments("4", 4)
            );
        }
    }

    /*
    해당 테스트에서 제공하는 기능 이외에 더 많은 기능들이 있습니다.
    - @RepeatedTest
    - @BeforeEach
    - @AfterEach
    - @BeforeAll
    - @AfterAll
    - assertNotEquals
    - assertNotSame
    - assertArrayEquals
    - assertIterableEquals
    - assertLinesMatch
    - assertThrowsExactly
    - assertTimeout
    - assertInstanceOf
    - ...

    참고: https://junit.org/junit5/docs/current/user-guide/

    필요하다면 추가적으로 위의 기능들을 학습해보세요.
     */
}
