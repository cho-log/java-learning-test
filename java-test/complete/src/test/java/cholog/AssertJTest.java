package cholog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * {@link JUnit5Test}부터 학습하고 오는 것을 추천드립니다.
 * <p>
 * AssertJ는 테스트 코드를 작성할 때 더 편리하게 작성할 수 있도록 도와주는 라이브러리입니다.
 * AssertJ가 JUnit을 대체하는 것은 아니고, JUnit과 함께 사용할 수 있습니다.
 * 같이 사용하면 보다 더 다양한 메서드를 사용할 수 있고, 테스트 코드를 작성할 때 더 편리하게 작성할 수 있습니다.
 * <p>
 * 참고: <a href="https://assertj.github.io/doc/">AssertJ 공식 문서</a>
 */
public class AssertJTest {
    /**
     * AssertJ의 `assertThat` 메서드는 JUnit5의 `assertThat` 메서드와 비슷한 기능을 합니다.
     */
    @Nested
    @DisplayName("assertThat 메서드 학습 테스트")
    class AssertThatTest {
        /**
         * isEqualTo는 두 객체의 값을 비교할 때 사용합니다.
         * JUnit5의 `assertEquals` 메서드와 비슷한 기능을 합니다.
         * JUnit5의 `assertEquals` 메서드를 AssertJ의 `isEqualTo` 메서드로 변경해보세요.
         */
        @Test
        @DisplayName("isEqualTo 메서드로 두 값이 같은지 비교한다")
        void isEqualTo_메서드로_두_값이_같은지_비교한다() {
            final var a = 1;
            final var b = 2;
            final var actual = a + b;

            final var expected = 3;

            assertThat(actual).isEqualTo(expected);
        }

        /**
         * isNotEqualTo는 두 객체의 값을 비교할 때 사용합니다.
         * JUnit5의 `assertNotEquals` 메서드와 비슷한 기능을 합니다.
         * JUnit5의 `assertNotEquals` 메서드를 AssertJ의 `isNotEqualTo` 메서드로 변경해보세요.
         */
        @Test
        @DisplayName("isNotEqualTo 메서드로 두 값이 다른지 비교한다")
        void isNotEqualTo_메서드로_두_값이_다른지_비교한다() {
            final var a = 1;
            final var b = 2;
            final var actual = a + b;

            final var expected = 4;

            assertThat(actual).isNotEqualTo(expected);
        }

        /**
         * isNull은 객체가 null인지 비교할 때 사용합니다.
         * JUnit5의 `assertNull` 메서드와 비슷한 기능을 합니다.
         * JUnit5의 `assertNull` 메서드를 AssertJ의 `isNull` 메서드로 변경해보세요.
         */
        @Test
        @DisplayName("isNull 메서드로 객체가 null인지 비교한다")
        void isNull_메서드로_객체가_null인지_비교한다() {
            final Object actual = null;

            assertThat(actual).isNull();
        }

        /**
         * isNotNull은 객체가 null이 아닌지 비교할 때 사용합니다.
         * JUnit5의 `assertNotNull` 메서드와 비슷한 기능을 합니다.
         * JUnit5의 `assertNotNull` 메서드를 AssertJ의 `isNotNull` 메서드로 변경해보세요.
         */
        @Test
        @DisplayName("isNotNull 메서드로 객체가 null이 아닌지 비교한다")
        void isNotNull_메서드로_객체가_null이_아닌지_비교한다() {
            final Object actual = new Object();

            assertThat(actual).isNotNull();
        }

        /**
         * isSameAs는 두 객체가 같은 객체인지 비교할 때 사용합니다.
         * JUnit5의 `assertSame` 메서드와 비슷한 기능을 합니다.
         * JUnit5의 `assertSame` 메서드를 AssertJ의 `isSameAs` 메서드로 변경해보세요.
         */
        @Test
        @DisplayName("isSameAs 메서드로 두 객체가 같은 객체인지 비교한다")
        void isSameAs_메서드로_두_객체가_같은_객체인지_비교한다() {
            final var actual = new Object();
            final var expected = actual;

            assertThat(actual).isSameAs(expected);
        }

        /**
         * isNotSameAs는 두 객체가 같은 객체인지 비교할 때 사용합니다.
         * JUnit5의 `assertNotSame` 메서드와 비슷한 기능을 합니다.
         * JUnit5의 `assertNotSame` 메서드를 AssertJ의 `isNotSameAs` 메서드로 변경해보세요.
         */
        @Test
        @DisplayName("isNotSameAs 메서드로 두 객체가 같은 객체인지 비교한다")
        void isNotSameAs_메서드로_두_객체가_같은_객체인지_비교한다() {
            final var actual = new Object();
            final var expected = new Object();

            assertThat(actual).isNotSameAs(expected);
        }

        /*
        `assertThat` 을 사용할 때 이런 기능 제공하나?라는 의문이 든다면 제공하고 있을 확률이 높습니다.
        앞으로 많은 테스트를 작성하며 필요한 기능이 있다면 먼저 AssertJ에 제공하는지 확인해보세요.

        참고: https://www.javadoc.io/doc/org.assertj/assertj-core/latest/org/assertj/core/api/Assertions.html#assertThat(T)
        참고: https://www.javadoc.io/doc/org.assertj/assertj-core/latest/org/assertj/core/api/package-summary.html
         */
    }

    /**
     * `assertThatThrownBy`는 특정 예외가 발생하는지 비교할 때 사용합니다.
     * JUnit5의 `assertThrows` 메서드와 비슷한 기능을 합니다.
     */
    @Nested
    @DisplayName("assertThatThrownBy 메서드 학습 테스트")
    class AssertThatThrownByTest {
        /**
         * JUnit5의 `assertThrows` 메서드를 AssertJ의 `assertThatThrownBy` 메서드로 변경해보세요.
         */
        @Test
        @DisplayName("assertThatThrownBy 메서드로 특정 예외가 발생하는지 비교한다")
        void assertThatThrownBy_메서드로_특정_예외가_발생하는지_비교한다() {
            assertThatThrownBy(() -> {
                causeException();
            }).isInstanceOf(IllegalCallerException.class);
        }

        /**
         * `assertThatThrownBy`를 사용하면 예외 타입 뿐만 아니라 예외 메시지까지 비교할 수 있습니다.
         * JUnit5의 `assertThrows` 메서드는 예외 타입만 비교할 수 있습니다.
         */
        @Test
        @DisplayName("assertThatThrownBy 메서드로 특정 예외가 발생하는지 비교한다")
        void assertThatThrownBy_메서드로_특정_예외_메시지가_발생하는지_비교한다() {
            assertThatThrownBy(() -> {
                causeException();
            }).isInstanceOf(IllegalCallerException.class)
                    .hasMessage("예외가 발생했습니다.");
        }

        private void causeException() {
            throw new IllegalCallerException("예외가 발생했습니다.");
        }
    }

    /**
     * `assertThatCode`는 특정 코드가 예외를 발생하지 않는지 비교할 때 사용합니다.
     * JUnit5의 `assertDoesNotThrow` 메서드와 비슷한 기능을 합니다.
     */
    @Nested
    @DisplayName("assertThatCode 메서드 학습 테스트")
    class AssertThatCodeTest {
        /**
         * JUnit5의 `assertDoesNotThrow` 메서드를 AssertJ의 `assertThatCode` 메서드로 변경해보세요.
         */
        @Test
        @DisplayName("assertThatCode 메서드로 특정 코드가 예외를 발생하지 않는지 비교한다")
        void assertThatCode_메서드로_특정_코드가_예외를_발생하지_않는지_비교한다() {
            assertThatCode(() -> {
                final var number = Integer.valueOf(0x80000000);
            }).doesNotThrowAnyException();
        }
    }

    /**
     * AssertJ는 `assertThat` 메서드의 매개변수로 넘기는 타입에 맞게 메서드를 사용할 수 있는 기능을 제공합니다.
     * String 타입의 매개변수를 `assertThat` 메서드의 매개변수로 넘기면 String 타입에 맞는 메서드를 사용할 수 있습니다.
     * <p>
     * 대표적인 몇가지 메서드를 살펴보겠습니다.
     */
    @Nested
    @DisplayName("String 타입 메서드 학습 테스트")
    class StringTest {
        /**
         * `contains`는 문자열에 특정 문자열이 포함되어 있는지 비교할 때 사용합니다.
         */
        @Test
        @DisplayName("contains 메서드로 문자열에 특정 문자열이 포함되어 있는지 비교한다")
        void contains_메서드로_문자열에_특정_문자열이_포함되어_있는지_비교한다() {
            final var actual = "Hello, world!";
            final var expected = "world";

            assertThat(actual).contains(expected);
        }

        /**
         * `startsWith`는 문자열이 특정 문자열로 시작하는지 비교할 때 사용합니다.
         */
        @Test
        @DisplayName("startsWith 메서드로 문자열이 특정 문자열로 시작하는지 비교한다")
        void startsWith_메서드로_문자열이_특정_문자열로_시작하는지_비교한다() {
            final var actual = "Hello, world!";
            final var expected = "Hello";

            assertThat(actual).startsWith(expected);
        }

        /**
         * AssertJ의 기능을 활용하여 문자열이 특정 문자열로 끝나는지 비교해보세요.
         */
        @Test
        @DisplayName("문자열이 특정 문자열로 끝나는지 비교한다")
        void 문자열이_특정_문자열로_끝나는지_비교한다() {
            final var actual = "Hello, world!";
            final var expected = "world!";

            assertThat(actual).endsWith(expected);
        }

        /**
         * AssertJ의 기능을 활용하여 문자열이 정규 표현식과 일치하는지 비교해보세요.
         */
        @Test
        @DisplayName("문자열이 정규 표현식과 일치하는지 비교한다")
        void matches_메서드로_문자열이_정규_표현식과_일치하는지_비교한다() {
            final var actual = "Hello, world!";
            final var expected = "Hello, [a-z]+!";

            assertThat(actual).matches(expected);
        }

        /*
        String을 검증할 수 있는 메서드는 위의 메서드들 말고도 더 있습니다.
        참고: https://www.javadoc.io/doc/org.assertj/assertj-core/latest/org/assertj/core/api/AbstractStringAssert.html

        필요하다면 추가적으로 위의 기능들을 학습해보세요.
        */
    }

    /**
     * Collection 타입의 매개변수를 `assertThat` 메서드의 매개변수로 넘기면 Collection 타입에 맞는 메서드를 사용할 수 있습니다.
     * <p>
     * 대표적인 몇가지 메서드를 살펴보겠습니다.
     */
    @Nested
    @DisplayName("Collection 타입 메서드 학습 테스트")
    class CollectionTest {
        /**
         * AssertJ의 기능을 활용하여 Collection의 크기를 비교해보세요.
         */
        @Test
        @DisplayName("Collection의 크기를 비교한다")
        void Collection의_크기를_비교한다() {
            final var actual = List.of(1, 2, 3);
            final var expected = 3;

            assertThat(actual).hasSize(expected);
        }

        /**
         * AssertJ의 기능을 활용하여 Collection에 특정 객체가 포함되어 있는지 비교해보세요.
         */
        @Test
        @DisplayName("Collection에 특정 객체가 포함되어 있는지 비교한다")
        void Collection에_특정_객체가_포함되어_있는지_비교한다() {
            final var actual = List.of(1, 2, 3);
            final var expected = 1;

            assertThat(actual).contains(expected);
        }

        /**
         * AssertJ의 기능을 활용하여 Collection에 특정 객체들이 포함되어 있는지 비교해보세요.
         */
        @Test
        @DisplayName("Collection에 특정 객체들이 포함되어 있는지 비교한다")
        void Collection에_특정_객체들이_포함되어_있는지_비교한다() {
            final var actual = List.of(1, 2, 3);
            final var expected = List.of(1, 2, 3);

            assertThat(actual).containsExactlyElementsOf(expected);

            /* ----- 아래는 추가로 학습할 분만 보세요! -----
            특정 객체들이 포함되어 있는지 비교하는 방법은 굉장히 많습니다. 또한 비슷한 기능도 굉장히 많습니다.
             - contains
             - containsAll
             - containsExactly
             - containsExactlyElementsOf
             - containsAnyElementsOf
             - containsExactlyInAnyOrder
             - containsExactlyInAnyOrderElementsOf

             위 메서드들은 어떠한 차이인지 학습해보세요.
             */
        }

        /**
         * `extracting` 메서드는 Collection에 포함된 객체들 중 특정 필드를 추출할 때 사용합니다.
         * <p>
         * `extracting` 메서드는 추출한 필드를 기반으로 메서드를 사용할 수 있습니다.
         * 추출한 필드를 기반으로 메서드를 사용하면 테스트 코드를 작성할 때 더 편리하게 작성할 수 있습니다.
         */
        @Test
        @DisplayName("extracting 메서드로 Collection에 포함된 객체들 중 특정 필드를 추출한다")
        void extracting_메서드로_Collection에_포함된_객체들_중_특정_필드를_추출한다() {
            class User {
                private final String username;
                private final String password;

                User(final String username, final String password) {
                    this.username = username;
                    this.password = password;
                }

                public String getUsername() {
                    return username;
                }
            }

            final var actual = List.of(
                    new User("user1", "password1"),
                    new User("user2", "password2"),
                    new User("user3", "password3")
            );
            final var expected = List.of("user1", "user2", "user3");

            assertThat(actual).extracting("username").containsExactlyElementsOf(expected);

            /* ----- 아래는 추가로 학습할 분만 보세요! -----
            `extracting`을 사용할 경우 getter가 없어도 필드값을 추출할 수 있습니다.
            그 이유는 `extracting` 메서드가 Reflection을 사용하기 때문입니다.
            Reflection은 객체의 필드나 메서드에 접근할 수 있도록 해주는 기능이지만, Reflection을 사용할 때는 주의해야 합니다.

            참고: https://docs.oracle.com/javase%2Ftutorial%2F/reflect/member/fieldValues.html
             */
        }

        /*
        Collection을 검증할 수 있는 메서드는 위의 메서드들 말고도 더 있습니다.
        참고: https://www.javadoc.io/static/org.assertj/assertj-core/3.25.1/org/assertj/core/api/AbstractCollectionAssert.html

        필요하다면 추가적으로 위의 기능들을 학습해보세요.
        */
    }

    /**
     * AssertJ는 Fluent API를 사용하여 메서드를 연속해서 사용할 수 있습니다.
     * Fluent API는 메서드를 연속해서 사용할 수 있도록 메서드를 연결하는 방식입니다.
     * 메서드를 연속해서 사용하면 테스트 코드를 작성할 때 더 편리하게 작성할 수 있습니다.
     * 또한 AssertJ는 메서드를 사용할 때 메서드 이름을 보고 어떤 기능을 하는지 쉽게 알 수 있도록 메서드 이름을 지었기 때문에 메서드 이름을 보고 어떤 기능을 하는지 쉽게 알 수 있습니다.
     * <p>
     * 참고: <a href="https://en.wikipedia.org/wiki/Fluent_interface">Fluent interface</a>
     * 참고: <a href="https://en.wikipedia.org/wiki/Method_chaining">Method chaining</a>
     * 참고: <a href="https://assertj.github.io/doc/#assertj-core-assertions-guide">AssertJ 메서드 이름 가이드</a>
     */
    @Test
    @DisplayName("chaining을 사용하여 여러 개의 메서드를 연속해서 사용할 수 있다")
    void chaining_을_사용하여_여러_개의_메서드를_연속해서_사용할_수_있다() {
        final var actual = new Object();
        final var expected = actual;

        assertThat(actual).isNotNull()
                .isInstanceOf(Object.class)
                .isSameAs(expected);
    }
}
