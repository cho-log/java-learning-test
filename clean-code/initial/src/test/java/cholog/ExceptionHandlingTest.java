package cholog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * 먼저 예외에 대한 기본적인 개념을 알아보겠습니다.
 * 예외(Exception)는 프로그램 내에 문제에 발생 했을 때 프로그램의 흐름을 제어할 수 있는 방법 중 하나입니다.
 * 예외는 컴파일 타임에 확인되는 컴파일 타임 예외(Checked Exception)와 런타임에 확인되는 런타임 예외(Unchecked Exception)로 나뉩니다.
 * <p>
 * 참고: <a href="https://en.wikipedia.org/wiki/Compile_time">컴파일 타임</a>
 * 참고: <a href="https://en.wikipedia.org/wiki/Execution_(computing)#Runtime">런타임</a>
 */
public class ExceptionHandlingTest {
    /**
     * 컴파일 타임 예외는 컴파일 타임에 확인되는 예외입니다.
     * 컴파일 타임 예외는 반드시 예외 처리를 해야 하고, 예외 처리를 하지 않으면 컴파일 에러가 발생합니다.
     */
    @Nested
    @DisplayName("컴파일 타임 예외")
    class CheckedExceptionTest {
        /**
         * 컴파일 타임에 확인된다는 의미는 개발하는 단계에서 문제 상황을 인지할 수 있어 런타임에 발생할 수 있는 문제를 예방할 수 있다는 장점이 있습니다.
         * 하지만, 예외 처리를 강제하기 때문에 매번 예외 처리를 해야 하는 번거로움이 있습니다.
         * 컴파일 타임 예외는 Exception 클래스를 상속받아서 만들 수 있습니다.
         */
        static final class CheckedException extends Exception {
        }

        /**
         * 컴파일 타임 예외를 발생시키는 테스트입니다.
         * 컴파일 타임 예외는 반드시 예외 처리를 해야 합니다.
         * 예외 처리를 하지 않으면 컴파일 에러가 발생합니다.
         */
        @Test
        @DisplayName("예외 처리를 하지 않으면 컴파일 에러가 발생한다")
        void 예외_처리를_하지_않으면_컴파일_에러가_발생한다() {
            // Note: 컴파일 타임 예외의 경우 처리하지 않으면 컴파일이 불가능하다.
            // TODO: 아래 코드의 주석을 풀어 컴파일 에러를 확인해보세요.
            // throw new CheckedException();
        }

        /**
         * 컴파일 타임 예외를 처리하는 방법은 try-catch 문을 사용하는 방법과 throws 키워드를 사용하는 방법이 있습니다.
         * <p>
         * try-catch 문은 예외가 발생할 수 있는 코드를 try 블록에 작성하고, 예외가 발생하면 catch 블록에서 예외를 처리합니다.
         * throws 키워드는 메서드에서 발생할 수 있는 예외를 호출하는 쪽으로 던지는 방법입니다.
         */
        @Test
        @DisplayName("try-catch문으로 예외 처리를 하면 컴파일 에러가 발생하지 않는다")
        void try_catch문으로_예외_처리를_하면_컴파일_에러가_발생하지_않는다() {
            try {
                throw new CheckedException();
            } catch (final CheckedException e) {
                // Note: 컴파일 타임 예외의 경우 처리하면 컴파일이 가능하다.
            }
        }

        @Test
        @DisplayName("throws 키워드로 예외 처리를 하면 컴파일 에러가 발생하지 않는다")
        void throws_키워드로_예외_처리를_하면_컴파일_에러가_발생하지_않는다()
                throws CheckedException { // Note: 메서드에서 발생할 수 있는 예외를 호출하는 쪽으로 던지면 컴파일이 가능하다.
            throw new CheckedException();
        }
    }

    @Nested
    @DisplayName("런타임 예외")
    class UncheckedExceptionTest {
        /**
         * 런타임 예외는 런타임에 확인되는 예외입니다.
         * 런타임 예외는 예외 처리를 강제하지 않기 때문에 예외 처리를 하지 않아도 컴파일 에러가 발생하지 않습니다.
         * 런타임 예외는 RuntimeException 클래스를 상속받아서 만들 수 있습니다.
         */
        static final class UncheckedException extends RuntimeException {
        }

        /**
         * 런타임 예외를 발생시키는 테스트입니다.
         * 런타임 예외는 예외 처리를 강제하지 않기 때문에 예외 처리를 하지 않아도 컴파일 에러가 발생하지 않습니다.
         */
        @Test
        @DisplayName("예외 처리를 하지 않아도 컴파일 에러가 발생하지 않는다")
        void 예외_처리를_하지_않아도_컴파일_에러가_발생하지_않는다() {
            // Note: 런타임 예외의 경우 처리하지 않아도 컴파일이 가능하다.
            assertThatThrownBy(() -> {
                throw new UncheckedException();
            }).isInstanceOf(UncheckedException.class);
        }

        /**
         * 컴파일 타임 예외와 동일하게 try-catch와 throws 키워드로 예외 처리가 가능합니다.
         * 차이는 예외 처리를 강제하지 않는다는 점입니다.
         */
        @Test
        @DisplayName("try-catch문으로 예외 처리하지 않아도 컴파일 에러가 발생하지 않는다")
        void try_catch문으로_예외_처리하지_않아도_컴파일_에러가_발생하지_않는다() {
            try {
                throw new UncheckedException();
            } catch (final UncheckedException e) {
                // Note: 런타임 예외의 경우 처리하지 않아도 컴파일이 가능하다.
            }
        }

        @Test
        @DisplayName("throws 키워드로 예외 처리하지 않아도 컴파일 에러가 발생하지 않는다")
        void throws_키워드로_예외_처리하지_않아도_컴파일_에러가_발생하지_않는다()
                throws UncheckedException { // Note: 런타임 예외의 경우 처리하지 않아도 컴파일이 가능하다.
            throw new UncheckedException();
        }
    }

    /**
     * 자바에서 제공하는 예외 처리 문법에 대해 알아보겠습니다.
     */
    @Nested
    @DisplayName("문법 학습")
    class SyntaxTest {
        /**
         * 여러 예외상황이 발생할 수 있는 코드를 작성할 때는 catch 블록을 여러 개 사용할 수 있습니다.
         */
        @Test
        @DisplayName("여러 예외상황이 발생할 수 있는 코드를 작성할 때는 catch 블록을 여러 개 사용할 수 있다")
        void 여러_예외상황이_발생할_수_있는_코드를_작성할_때는_catch_블록을_여러_개_사용할_수_있다() {
            class FirstException extends Exception {
            }
            class SecondException extends Exception {
            }

            try {
                int randomValue = new Random().nextBoolean() ? 1 : 2;

                if (randomValue == 1) {
                    throw new FirstException();
                } else if (randomValue == 2) {
                    throw new SecondException();
                }
            } catch (final FirstException e) {
                System.out.println("예외 처리 성공");
            } catch (final SecondException e) {
                System.out.println("예외 처리 성공");
            }
        }

        /**
         * catch 블록을 여러 개 사용할 때 주의할 점은 상위 클래스의 예외를 먼저 처리해야 합니다.
         * 상위 클래스란 상속 관계에서 상위에 있는 클래스를 의미합니다.
         * 상위 클래스의 예외를 먼저 처리하지 않으면 하위 클래스의 예외를 처리할 수 없어 컴파일 에러가 발생합니다.
         */
        @Test
        @DisplayName("상위 클래스의 예외를 먼저 처리하지 않으면 컴파일 에러가 발생한다")
        void 상위_클래스의_예외를_먼저_처리하지_않으면_컴파일_에러가_발생한다() {
            class SuperException extends Exception {
            }
            class ChildException extends SuperException {
            }

            try {
                int randomValue = new Random().nextBoolean() ? 1 : 2;

                if (randomValue == 1) {
                    throw new SuperException();
                } else if (randomValue == 2) {
                    throw new ChildException();
                }
            } catch (final SuperException e) {
                System.out.println("예외 처리 성공");
            }
            /* TODO: 아래 코드의 주석을 풀어 예외 처리를 통해 컴파일 에러를 해결해보세요.
            catch (final ChildException e) { // Note: 상위 클래스의 예외를 먼저 처리하지 않으면 컴파일 에러가 발생한다.
                System.out.println("예외 처리 성공");
            }
             */

        }

        /**
         * catch 블록 내부에서 동일한 처리를 한다면 catch 블록을 하나로 합칠 수 있습니다.
         * 최상위 예외인 Exception 클래스의 경우 모든 예외를 처리할 수 있지만,
         * 해당 코드를 작성한 개발자가 아닌 내부에서 발생하는 예외도 처리할 수 있기 때문에 최대한 사용을 지양하는 것이 좋습니다.
         */
        @Test
        @DisplayName("catch 블록 내부에서 동일한 처리를 한다면 catch 블록을 하나로 합칠 수 있다")
        void catch_블록_내부에서_동일한_처리를_한다면_catch_블록을_하나로_합칠_수_있다() {
            class SuperException extends Exception {
            }
            class FirstException extends SuperException {
            }
            class SecondException extends SuperException {
            }

            try {
                int randomValue = new Random().nextBoolean() ? 1 : 2;

                if (randomValue == 1) {
                    throw new FirstException();
                } else if (randomValue == 2) {
                    throw new SecondException();
                }
            } catch (final SuperException e) { // Note: catch 블록 내부에서 동일한 처리를 한다면 catch 블록을 하나로 합칠 수 있다.
                System.out.println("예외 처리 성공");
            }
        }

        /**
         * 만약 catch 블록 내부에서 동일한 처리를 하지만 예외 타입이 다르다면 multiple catch 블록을 사용할 수 있습니다.
         * multiple catch 블록은 자바 7부터 지원합니다.
         * <p>
         * 참고: <a href="https://docs.oracle.com/javase/7/docs/technotes/guides/language/catch-multiple.html">Multiple Catch Blocks</a>
         */
        @Test
        @DisplayName("catch 블록 내부에서 동일한 처리를 하지만 예외 타입이 다르다면 catch 블록을 하나로 합칠 수 없다")
        void catch_블록_내부에서_동일한_처리를_하지만_예외_타입이_다르다면_catch_블록을_하나로_합칠_수_없다() {
            class SuperFirstException extends Exception {
            }
            class SuperSecondException extends Exception {
            }

            class FirstException extends SuperFirstException {
            }
            class SecondException extends SuperSecondException {
            }

            try {
                int randomValue = new Random().nextBoolean() ? 1 : 2;

                if (randomValue == 1) {
                    throw new FirstException();
                } else if (randomValue == 2) {
                    throw new SecondException();
                }
            } catch (final FirstException | SecondException e) {
                System.out.println("예외 처리 성공");
            }
        }
    }

    /**
     * 예외 처리 문법을 사용하는 것은 단순하지만 좋은 예외 처리를 하는 것은 어렵습니다.
     * 좋은 예외 처리를 하기 위해서는 예외 처리에 대한 이해와 경험이 필요합니다.
     * 여러 케이스를 통해 좋은 예외 처리를 알아보겠습니다.
     */
    @Nested
    @DisplayName("좋은 예외 처리")
    class GoodExceptionHandling {
        /**
         * 유저를 생성하는 사례를 통해 Checked Exception과 Unchecked Exception의 차이를 알아보겠습니다.
         */
        @Nested
        class UserTest {
            /**
             * 문제가 있는 상황에서 예외 처리를 하지 않고 null을 반환하는 것은 예상치 못한 문제를 발생시킬 수 있습니다.
             * 유저의 이름의 최대 길이가 5자로 제한되어 있을 때 예외 처리를 어떻게 하는 것이 좋을까?
             */
            @Test
            @DisplayName("유저의 이름의 최대 길이가 5자로 제한되어 있을 때 예외 처리를 어떻게 하는 것이 좋을까?")
            void 유저의_이름의_최대_길이가_5자로_제한되어_있을_때_예외_처리를_어떻게_하는_것이_좋을까() {
                record User(String name) {
                    static User create(final String name) {
                        if (name.length() > 5) {
                            // TODO: 어떻게 예외 처리를 하는 것이 좋을지 고민 후 코드로 작성해보세요.
                            return null;
                        }
                        return new User(name);
                    }
                }

                // TODO: 의도에 맞게 동작하는지 JUnit, AssertJ를 사용하여 확인해보세요.
            }

            /**
             * 컴파일 타임 예외의 경우 처리하지 않으면 컴파일이 불가능하다는 특징이 있습니다.
             * 컴파일 타임 예외를 발생시키면 개발자에게 예외 처리를 강제할 수 있지만, 예외 처리를 강제하기 때문에 매번 예외 처리를 해야 하는 번거로움이 있습니다.
             * 개발자가 예외 처리를 하지 않고 넘어가는 상황을 만들 수 있습니다.
             * 예외 처리를 강제하지 않는 코드를 어떻게 작성할 수 있을까?
             */
            @Test
            @DisplayName("예외 처리를 강제하지 않는 코드를 어떻게 작성할 수 있을까?")
            void 예외_처리를_강제하지_않는_코드를_어떻게_작성할_수_있을까() {
                record User(String name) {
                    static User create(final String name) throws Exception {
                        if (name.length() > 5) {
                            // TODO: 예외 처리를 강제하지 않는 코드를 어떻게 작성할 수 있을까?
                            throw new Exception("이름의 길이는 5자를 넘을 수 없습니다.");
                        }
                        return new User(name);
                    }
                }

                // TODO: 의도에 맞게 동작하는지 JUnit, AssertJ를 사용하여 확인해보세요.
            }

            /**
             * 요즘 나오는 언어들은 컴파일 타임 예외를 지원하지 않는 경우가 많습니다.
             * 이유는 컴파일 타임 예외를 사용했을 때 얻을 수 있는 이점보다 생길 수 있는 부작용이 더 크기 때문입니다.
             * 따라서 특별한 경우가 아니면 컴파일 타임 예외를 사용하지 않는 것이 좋습니다.
             * <p>
             * 런타임 예외의 경우 처리하지 않아도 컴파일이 가능하다는 특징이 있습니다.
             * 런타임 예외를 처리하지 않으면 예외가 발생했을 때 프로그램이 강제 종료될 수 있으니 주의가 필요합니다.
             */
            @Test
            @DisplayName("런타임 예외를 처리하지 않으면 예외가 발생했을 때 프로그램이 강제 종료될 수 있다")
            void 유저의_이름이_최대_길이가_5자로_제한되어_있다는_의도를_나타내는_테스트는_어떻게_작성할_수_있을까() {
                record User(String name) {
                    static User create(final String name) {
                        if (name.length() > 5) {
                            throw new IllegalArgumentException("이름의 길이는 5자를 넘을 수 없습니다.");
                        }
                        return new User(name);
                    }
                }

                assertAll(
                        () -> assertThatCode(() -> User.create("12345"))
                                .doesNotThrowAnyException(),
                        () -> assertThatThrownBy(() -> User.create("123456"))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage("이름의 길이는 5자를 넘을 수 없습니다.")
                );
            }

            /**
             * 유저 생성 시 여러 예외 상황이 발생할 수 있을 때 어떻게 예외 처리를 하는 것이 좋을까?
             */
            @Test
            @DisplayName("유저 생성 시 여러 예외 상황이 발생할 수 있을 때 어떻게 예외 처리를 하는 것이 좋을까?")
            void 유저_생성_시_여러_예외_상황이_발생할_수_있을_때_어떻게_예외_처리를_하는_것이_좋을까() {
                record User(String name) {
                    // TODO: 모든 케이스를 나눠서 예외 처리 하는 것이 좋을지 고민 후 리팩토링 해보세요.
                    static User create(final String name) {
                        if (name == null) {
                            throw new IllegalArgumentException("이름이 Null일 수 없습니다.");
                        }
                        if (name.isEmpty()) {
                            throw new IllegalArgumentException("이름이 빈 값 일 수 없습니다.");
                        }
                        if (name.isBlank()) {
                            throw new IllegalArgumentException("이름에 공백만 존재할 수 없습니다.");
                        }
                        if (name.length() > 5) {
                            throw new IllegalArgumentException("이름의 길이는 5자를 넘을 수 없습니다.");
                        }
                        return new User(name);
                    }
                }

                // TODO: 의도에 맞게 동작하는지 JUnit, AssertJ를 사용하여 확인해보세요.
            }

            /**
             * 해당 문제는 유저의 생성을 어느 레벨까지 추상화할지에 따라 달라집니다.
             * 단순 생성을 할 수 있는지를 확인하는 것이라면 하나의 예외로 처리할 수 있습니다.
             * 하지만 하나의 예외로 처리한다면 정확히 어떠한 문제가 발생했는지 알 수 없고, 예외 메시지 또한 정확하지 않을 수 있습니다.
             * 예외를 적절한 레벨까지 추상화한다면 어떻게 구분할 수 있을까?
             */
            @Test
            @DisplayName("예외를 적절한 레벨까지 추상화한다면 어떻게 구분할 수 있을까?")
            void 유저_생성에_대해_더_많은_정보를_전달해야_한다면_어떻게_예외_처리를_하는_것이_좋을까() {
                record User(String name) {
                    static User create(final String name) {
                        // TODO: 예외 상황을 적절한 레벨로 추상화하여 예외 처리를 분리해보세요.
                        if (name == null || name.isBlank() || name.length() > 5) {
                            throw new IllegalArgumentException("유저 생성에 실패했습니다.");
                        }
                        return new User(name);
                    }
                }

                // TODO: 의도에 맞게 동작하는지 JUnit, AssertJ를 사용하여 확인해보세요.
            }

            /**
             * 입력 값 자체가 올바르지 않은 상황과 생성 조건에 맞지 않는 상황을 구분하여 예외 처리를 할 수 있습니다.
             * 이렇게 구분이 되면 예외 메시지를 더 정확하게 전달할 수 있고, 예외를 더 적절한 레벨까지 추상화할 수 있습니다.
             * 또한 메시지를 통해 어떠한 문제가 발생했는지 알 수 있습니다.
             * 메시지를 기준으로 예외를 구분하기 위해서는 catch 블록 내에서 메시지를 비교해야 하는데, 외부에서 쉽게 예외를 구분할 수 있도록 하는 방법은 없을까?
             */
            @Test
            @DisplayName("외부에서 쉽게 예외를 구분할 수 있도록 하는 방법은 없을까?")
            void 외부에서_쉽게_예외를_구분할_수_있도록_하는_방법은_없을까() {
                record User(String name) {
                    // TODO: 외부에서 메시지가 아닌 다른 방법으로 구분할 수 있도록 리팩토링 해보세요.
                    static User create(final String name) {
                        if (name == null || name.isBlank()) {
                            throw new IllegalArgumentException("유저 이름이 올바르지 않습니다.");
                        }
                        if (name.length() > 5) {
                            throw new IllegalArgumentException("유저 이름의 길이는 5자를 넘을 수 없습니다.");
                        }
                        return new User(name);
                    }
                }

                // TODO: 의도에 맞게 동작하는지 JUnit, AssertJ를 사용하여 확인해보세요.
            }

            /**
             * 외부에서 쉽게 예외를 구분할 수 있도록 하는 방법은 예외 클래스를 상속받아 새로운 예외 클래스를 만드는 것입니다.
             * 새로운 예외 클래스를 만들어서 예외를 구분하면 예외 메시지를 비교하는 것보다 더 쉽게 예외를 구분할 수 있습니다.
             * 또한 새로운 예외 클래스를 만들어서 예외를 구분하면 예외를 더 적절한 레벨까지 추상화할 수 있습니다.
             */
            @Test
            @DisplayName("새로운 예외 클래스를 만들어서 예외를 구분하면 예외를 더 적절한 레벨까지 추상화할 수 있다")
            void 새로운_예외_클래스를_만들어서_예외를_구분하면_예외를_더_적절한_레벨까지_추상화할_수_있다() {
                record User(String name) {
                    static class MalformedUserNameException extends IllegalArgumentException {
                    }

                    static User create(final String name) {
                        if (name == null || name.isBlank()) {
                            throw new IllegalArgumentException("유저 이름이 올바르지 않습니다.");
                        }
                        if (name.length() > 5) {
                            throw new MalformedUserNameException();
                        }
                        return new User(name);
                    }
                }
            }
        }

        /**
         * 자판기 예시를 통해 예외 상황에서 처리 방법을 통해 의도를 나타내는 방법을 알아보겠습니다.
         */
        @Nested
        class VendingMachineTest {
            record Item(String name) {
            }

            static abstract class VendingMachine {
                private final List<Item> items = Stream.of("콜라", "사이다", "환타", "펩시", "마운틴듀", "스프라이트", "게토레이", "파워에이드", "밀키스")
                        .map(Item::new)
                        .toList();

                Item selectItemByName(final String name) {
                    return items.stream()
                            .filter(item -> item.name().equals(name))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("해당 아이템이 존재하지 않습니다."));
                }
            }

            /**
             * 해당 자판기가 가진 엔진은 오래된 엔진으로 아이템을 뽑을 때 가끔 실패할 수 있습니다.
             * 사용자는 내부에서 어떻게 동작하는지는 주 관심사가 아닙니다.
             * 내부 동작 상관 없이 의도된 아이템을 뽑도록 어떻게 예외 처리를 하는 것이 좋을까?
             */
            @Test
            @DisplayName("내부 동작 상관 없이 의도된 아이템을 뽑도록 어떻게 예외 처리를 하는 것이 좋을까?")
            void 내부_동작_상관_없이_의도된_아이템을_뽑도록_어떻게_예외_처리를_하는_것이_좋을까() {
                class OldVendingMachine extends VendingMachine {
                    @Override
                    Item selectItemByName(final String name) {
                        if (ThreadLocalRandom.current().nextBoolean()) {
                            throw new IllegalStateException("아이템을 뽑는 데 실패했습니다.");
                        }

                        return super.selectItemByName(name);
                    }
                }

                final class CustomVendingMachine extends OldVendingMachine {
                    @Override
                    Item selectItemByName(final String name) {
                        // TODO: 내부 동작 상관 없이 의도된 아이템을 뽑도록 어떻게 예외 처리를 하는 것이 좋을까?
                        return super.selectItemByName(name);
                    }
                }

                final var vendingMachine = new CustomVendingMachine();

                // TODO: 의도에 맞게 동작하는지 JUnit, AssertJ를 사용하여 확인해보세요.
            }

            /**
             * 실패하면 재시도하는 방법을 복구라고 합니다.
             * 복구는 예외가 발생한 문제를 정상 상태로 돌려놓는 것을 의미하며, 호출하는 쪽에서는 복구 작업이 일어났는지 알 수 없습니다.
             * 즉, 복구는 외부에 문제가 발생했는지 여부를 알 필요가 없고 내부적으로 문제를 해결하는 경우 사용합니다.
             * 내부 문제가 있다면 외부에서 처리하도록 하는 방법은 없을까?
             */
            @Test
            @DisplayName("내부 문제가 있다면 외부에서 처리하도록 하는 방법은 없을까?")
            void 내부_문제가_있다면_외부에서_처리하도록_하는_방법은_없을까() {
                class BrokenVendingMachine extends VendingMachine {
                    @Override
                    Item selectItemByName(final String name) {
                        throw new IllegalArgumentException("아이템을 뽑는 데 실패했습니다.");
                    }
                }

                final class CustomVendingMachine extends BrokenVendingMachine {
                    // TODO: 내부 문제가 있다면 외부에서 처리하도록 하는 방법은 없을까?
                    @Override
                    Item selectItemByName(final String name) {
                        try {
                            return super.selectItemByName(name);
                        } catch (final IllegalStateException e) {
                            // Note: 한 가지 방법을 재시도하는 것은 하나의 방법일 뿐 내부 정책에 따라 복구하는 방법은 달라질 수 있습니다. 예를 들어 해당 아이템을 뽑는데 실패했다면, 다음 아이템을 주는 방식도 복구라고 할 수 있습니다.
                            return selectItemByName(name);
                        }
                    }
                }

                final var vendingMachine = new CustomVendingMachine();

                // TODO: 의도에 맞게 동작하는지 JUnit, AssertJ를 사용하여 확인해보세요.
            }

            /**
             * 문제가 발생했을 때 외부로 넘기는 방식을 회피라고 합니다.
             * 회피는 내부에서 처리하지 않고 호출하는 쪽에서 예외 상황을 처리할 수 있도록 하는 방식을 의미하며, 내부에서는 어떻게 처리되는지 알 수 없습니다.
             * 즉, 회피는 내부에서 책임지지 않고 외부에 100% 책임을 전가하는 방식입니다.
             * 외부에 책임을 전가할 경우 코드를 작성하기엔 매우 간단하지만, 코드의 응집도가 떨어지고, 외부에서 예외 처리를 해야 하기 때문에 외부에서 예외 처리를 잊어버리는 경우가 발생할 수 있습니다.
             * 따라서 가능한 회피를 사용하는 것은 지양하는 것이 좋습니다.
             */
            @Test
            @DisplayName("회피를 사용하는 것은 지양하는 것이 좋습니다")
            void 회피를_사용하는_것은_지양하는_것이_좋습니다() {
                class BrokenVendingMachine extends VendingMachine {
                    @Override
                    Item selectItemByName(final String name) {
                        throw new IllegalStateException("아이템을 뽑는 데 실패했습니다.");
                    }
                }

                final class CustomVendingMachine extends BrokenVendingMachine {
                    @Override
                    Item selectItemByName(final String name) throws IllegalArgumentException {
                        return super.selectItemByName(name);
                    }
                }

                final var vendingMachine = new CustomVendingMachine();

                assertThatThrownBy(() -> vendingMachine.selectItemByName("콜라"))
                        .isInstanceOf(IllegalStateException.class)
                        .hasMessage("아이템을 뽑는 데 실패했습니다.");
            }

            /**
             * 회피하는 방식보다 더 무책임한 방식이 무시하는 방식입니다.
             * 무시하는 방식은 예외가 발생했을 때 아무런 처리를 하지 않는 방식을 의미합니다.
             * 해당 자판기를 가진 상점에서 자판기가 고장났을 때 무시하는 상황이라 가정해봅시다.
             */
            @Test
            @DisplayName("무책임한 방식이 무시하는 방식입니다")
            void 무책임한_방식이_무시하는_방식입니다() {
                class BrokenVendingMachine extends VendingMachine {
                    @Override
                    Item selectItemByName(final String name) {
                        throw new IllegalStateException("아이템을 뽑는 데 실패했습니다.");
                    }
                }

                final class Store {
                    private final VendingMachine vendingMachine = new BrokenVendingMachine();
                    private final List<Item> soldItems = new ArrayList<>();

                    void orderFromVendingMachine(final String name) {
                        try {
                            final var item = vendingMachine.selectItemByName(name);
                            soldItems.add(item);
                        } catch (final IllegalStateException ignored) {
                            // Note: 내부에서도 조치하지 않고 외부에서도 문제가 발생했다는 사실을 알 수 없기 때문에 가능한 무시하는 방식은 지양하는 것이 좋습니다.
                        }
                    }
                }

                final var store = new Store();
                store.orderFromVendingMachine("콜라");

                assertThat(store.soldItems).isEmpty();
            }


            /**
             * 해당 자판기를 가진 상점에서 자판기가 고장났을 때 상점에서 처리하지 않고 자판기 회사로 직접 연락하길 원하는 상황이라 가정해봅시다.
             * 이러한 상황이 의도 됐다면 회피를 하는 것이 좋은 방법일 때가 있습니다.
             * 하지만 회피를 하는 코드를 작성한다면 코드를 봤을 때 해당 상황이 의도된 회피인지 확인할 수 없습니다.
             * 해당 상황이 의도된 회피인지 확인할 수 있도록 의도를 나타내는 방법은 없을까?
             */
            @Test
            @DisplayName("의도된 회피인지 확인할 수 있도록 의도를 나타내는 방법은 없을까?")
            void 의도된_회피인지_확인할_수_있도록_의도를_나타내는_방법은_없을까() {
                class BrokenVendingMachine extends VendingMachine {
                    @Override
                    Item selectItemByName(final String name) {
                        throw new IllegalStateException("아이템을 뽑는 데 실패했습니다.");
                    }
                }

                final class Store {
                    private final VendingMachine vendingMachine = new BrokenVendingMachine();
                    private final List<Item> soldItems = new ArrayList<>();

                    // TODO: 의도된 회피인지 확인할 수 있도록 의도를 나타내는 방법은 없을까?
                    void orderFromVendingMachine(final String name) {
                        final var item = vendingMachine.selectItemByName(name);
                        soldItems.add(item);
                    }
                }

                final var store = new Store();

                // TODO: 의도에 맞게 동작하는지 JUnit, AssertJ를 사용하여 확인해보세요.
            }

            /**
             * 실패하면 외부에서 처리하되 추상화 레벨에 맞게 실패한 이유를 변경하는 방법을 전환이라고 합니다.
             * 회피와 비슷해 보이지만 의도를 들어낼 수 있다는 점이 다릅니다.
             * 따라서 회피하는 코드를 작성한다면 전환을 통해 의도를 들어낼 수 있도록 리팩토링하는 것이 좋습니다.
             */
            @Test
            @DisplayName("회피하는 코드를 작성한다면 전환을 통해 의도를 들어낼 수 있도록 리팩토링하는 것이 좋습니다")
            void 회피하는_코드를_작성한다면_전환을_통해_의도를_들어낼_수_있도록_리팩토링하는_것이_좋습니다() {
                class BrokenVendingMachine extends VendingMachine {
                    @Override
                    Item selectItemByName(final String name) {
                        throw new IllegalStateException("아이템을 뽑는 데 실패했습니다.");
                    }
                }

                final class Store {
                    private final VendingMachine vendingMachine = new BrokenVendingMachine();
                    private final List<Item> soldItems = new ArrayList<>();

                    void orderFromVendingMachine(final String name) {
                        try {
                            final var item = vendingMachine.selectItemByName(name);
                            soldItems.add(item);
                        } catch (final IllegalStateException e) {
                            throw new IllegalStateException("자판기 회사에 문의하세요.", e);
                        }
                    }
                }

                final var store = new Store();

                assertThatThrownBy(() -> store.orderFromVendingMachine("콜라"))
                        .isInstanceOf(IllegalStateException.class)
                        .hasMessage("자판기 회사에 문의하세요.");
            }

            /**
             * 다시 돌아와서 악덕 사장으로 빙의해봅시다.
             * 자판기가 고장났을 때 돈을 먹고 아무것도 주지 않는다면 양쪽으로 이득을 볼 수 있는 상황이라 판단하여 일부러 고장내고 장사를 할 생각입니다.
             * 그렇다면 무시하는 방식이 가장 적합한 방식일 것입니다.
             */
            @Test
            @DisplayName("무시하는 방식이 가장 적합한 방식일 것입니다")
            void 무시하는_방식이_가장_적합한_방식일_것입니다() {
                class BrokenVendingMachine extends VendingMachine {
                    @Override
                    Item selectItemByName(final String name) {
                        throw new IllegalArgumentException("아이템을 뽑는 데 실패했습니다.");
                    }
                }

                final class Store {
                    private final VendingMachine vendingMachine = new BrokenVendingMachine();
                    private final List<Item> soldItems = new ArrayList<>();

                    void orderFromVendingMachine(final String name) {
                        try {
                            final var item = vendingMachine.selectItemByName(name);
                            soldItems.add(item);
                        } catch (final IllegalStateException ignored) {
                            // Note: 의도된 무시기 때문에 문제가 있는 코드가 아니다.
                        }
                    }
                }

                final var store = new Store();

                assertThat(store.soldItems).isEmpty();
            }

            /*
            위 예제들을 통해 예외 처리를 복구, 회피, 무시하는 방식으로 나누어 설명하였습니다.
            복구는 예외가 발생한 문제를 정상 상태로 돌려놓는 것을 의미하며, 호출하는 쪽에서는 복구 작업이 일어났는지 알 수 없습니다.
            회피는 내부에서 처리하지 않고 호출하는 쪽에서 예외 상황을 처리할 수 있도록 하는 방식을 의미하며, 내부에서는 어떻게 처리되는지 알 수 없습니다.
            무시는 예외가 발생했을 때 아무런 처리를 하지 않는 방식을 의미하며, 내부에서도 조치하지 않고 외부에서도 문제가 발생했다는 사실을 알 수 없습니다.

            의도가 담기지 않은 회피와 무시는 가능한 사용하지 않는 것이 좋지만, 의도가 담겼다면 훌륭한 방법이 될 수 있습니다.
            좋은 예외 처리를 하기 위해서는 의도에 맞게 잘 처리하는 것이 중요하고, 잘 처리하기 위해서는 예외 처리에 대한 이해와 경험이 필요합니다.
            예외 처리를 할 때 마다 의식적으로 어떠한 의도를 담을지 고민하며 작성하면 좋은 예외 처리를 할 수 있습니다.
             */
        }
    }
}
