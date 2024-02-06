package cholog.goodcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * 좋은 코드의 기준은 사람마다 다르지만 대부분의 사람들이 동의하는 몇 가지 기준이 있습니다.
 * 그 기준 중 유지보수성과 확장성은 매우 중요한 기준입니다.
 * 가독성 높은 코드는 유지보수성을 높이고 버그를 줄이는데 도움을 줍니다.
 * 유지보수성과 확장성을 위한 읽기 좋은 코드를 작성하는 방법을 알아봅니다.
 */
@Nested
@DisplayName("가독성 높은 코드")
public class ReadableCodeTest {
    /**
     * 아래 코드는 최대 5까지만 움직이는 자동차를 구현한 코드입니다.
     * 한 눈에 봤을 때 코드의 의도를 파악하기 어렵습니다.
     * 어떻게 의도를 전달할 수 있을까?
     */
    @Test
    @DisplayName("어떻게 의도를 전달할 수 있을까?")
    void 어떻게_의도를_전달할_수_있을까() {
        class Car {
            // 자동차 위치
            private int p = 0;

            void forward() {
                if (p > 5) {
                    throw new IllegalStateException("최대 5까지만 움직일 수 있습니다.");
                }

                p += 1;
            }
        }

        final var car = new Car();

        car.forward();
        assertThat(car.p).isEqualTo(1);
    }

    /**
     * 주석을 사용하여 의도를 전달하는 방법입니다.
     * 하지만 주석을 신경쓰지 않고 코드를 변경하거나, 처음부터 주석과 다른 코드를 작성했다면 오히려 오해할 수 있는 코드가 될 수 있습니다.
     * 주석을 사용하지 않고도 의도를 전달할 수 있는 방법은 없을까?
     */
    @Test
    @DisplayName("주석을 사용하지 않고도 의도를 전달할 수 있는 방법은 없을까?")
    void 주석을_사용하지_않고도_의도를_전달할_수_있는_방법은_없을까() {
        class Car {
            private int position = 0;

            void forward() {
                if (position > 5) {
                    throw new IllegalStateException("최대 5까지만 움직일 수 있습니다.");
                }

                position += 1;
            }
        }

        final var car = new Car();

        car.forward();
        assertThat(car.position).isEqualTo(1);
    }

    /**
     * 주석을 사용하지 않고 의미있는 이름을 통해 의도를 전달하는 방법입니다.
     * 의미있는 이름을 사용하면 코드를 읽는 사람이 코드의 의도를 파악하기 쉬워집니다.
     * 또한 코드로 관리되기 때문에 코드 변경 시 주석을 신경쓰지 않아도 됩니다.
     * 지금의 position은 이름을 통해 의도를 전달하고 있지만, 최대 5까지만 움직인다는 사실은 동작을 통해 알 수 있습니다.
     * 코드를 통해 객체의 역할을 명확하게 드러내는 방법은 없을까?
     */
    @Test
    @DisplayName("코드를 통해 객체의 역할을 명확하게 드러내는 방법은 없을까")
    void 코드를_통해_객체의_역할을_명확하게_드러내는_방법은_없을까() {
        class Position {
            private final int value;

            public Position() {
                this(0);
            }

            public Position(final int value) {
                if (value > 5) {
                    throw new IllegalStateException("최대 5까지만 움직일 수 있습니다.");
                }

                this.value = value;
            }

            Position forward() {
                return new Position(value + 1);
            }

            @Override
            public boolean equals(final Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                final Position position = (Position) o;
                return value == position.value;
            }

            @Override
            public int hashCode() {
                return Objects.hash(value);
            }
        }

        class Car {
            private Position position = new Position();

            void forward() {
                position = position.forward();
            }
        }

        final var car = new Car();

        car.forward();
        assertThat(car.position).isEqualTo(new Position(1));
    }

    /**
     * 움직임을 담당하는 객체를 만들어서 코드를 통해 객체의 역할을 명확하게 드러내는 방법입니다.
     * 객체의 역할을 명확하게 드러내면 코드를 읽는 사람이 코드의 의도를 파악하기 쉬워집니다.
     * 하지만 의미없는 객체가 많이 생기게 된다면 유지보수성이 떨어질 수 있으니 적절한 추상화 수준을 유지하는 것이 중요합니다.
     * 코드 자체로 설명이 되도록 코드를 작성하면 유지 및 관리의 비용이 줄어듭니다.
     */
    @Test
    @DisplayName("코드 자체로 설명이 되도록 코드를 작성하면 유지 및 관리의 비용이 줄어든다")
    void 코드_자체로_설명이_되도록_코드를_작성하면_유지_및_관리의_비용이_줄어든다() {
        class Position {
            private final int value;

            public Position() {
                this(0);
            }

            public Position(final int value) {
                if (value > 5) {
                    throw new IllegalStateException("최대 5까지만 움직일 수 있습니다.");
                }

                this.value = value;
            }

            Position forward() {
                return new Position(value + 1);
            }

            @Override
            public boolean equals(final Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                final Position position = (Position) o;
                return value == position.value;
            }

            @Override
            public int hashCode() {
                return Objects.hash(value);
            }
        }

        class Car {
            private Position position = new Position();

            void forward() {
                position = position.forward();
            }
        }

        final var car = new Car();

        car.forward();
        assertThat(car.position).isEqualTo(new Position(1));
    }

    /**
     * 일관적이지 않은 코드 스타일은 코드를 읽는 사람이 코드를 이해하는데 혼동을 줄 수 있습니다.
     * 오해할 위험을 줄이면, 버그가 줄어들고 혼란스러운 코드를 이해하는데 낭비되는 시간을 줄일 수 있습니다.
     */
    @Test
    @DisplayName("일관된 코드 스타일을 가져간다")
    void 일관된_코드_스타일을_가져간다() {
        // @formatter:off
        class Car {
            private String name;
            private int position;

            public void forward() {
                position += 1;
            }

            public void backward() {
                position -= 1;
            }

            public String getName() {
                return name;
            }

            public int getPosition() {
                return position;
            }
        }
        // @formatter:on

        final var car = new Car();

        car.forward();
        assertThat(car.getPosition()).isEqualTo(1);
    }

    /**
     * 일관된 코드 스타일로 리팩토링한 코드입니다.
     * 코드를 이해하기 쉽게 만들기 위해 일관된 코드 스타일을 사용하는 것이 중요합니다.
     * 코드를 읽는 사람이 코드를 이해하는데 혼동을 줄어들어 코드를 이해하는데 낭비되는 시간을 줄일 수 있습니다.
     */
    @Test
    @DisplayName("일관된 코드 스타일로 리팩토링한 코드")
    void 일관된_코드_스타일로_리팩토링한_코드() {
        class Car {
            private String name;
            private int position;

            public void forward() {
                position += 1;
            }

            public void backward() {
                position -= 1;
            }

            public String getName() {
                return name;
            }

            public int getPosition() {
                return position;
            }
        }

        final var car = new Car();

        car.forward();
        assertThat(car.getPosition()).isEqualTo(1);
    }

    /**
     * 하나의 메서드가 많은 일을 하면 추상화 계층이 깊어지고, 코드를 이해하기 어려워집니다.
     * 메서드가 한 가지 일만 하도록 작성하면 코드를 이해하기 쉬워집니다.
     * 아래 우승 로또 번호와 나의 로또 번호를 비교하여 상금을 계산하는 코드는 한 가지 이상의 일을 하고 있습니다.
     * 어떻게 추상화하여 메서드를 작게 만들 수 있을까?
     */
    @Test
    @DisplayName("어떻게 추상화하여 메서드를 작게 만들 수 있을까?")
    void 어떻게_추상화하여_메서드를_작게_만들_수_있을까() {
        class LottoGame {
            int calculatePrize(
                    final List<Integer> numbers,
                    final List<Integer> winningNumbers
            ) {
                validateNumbers(numbers);
                validateNumbers(winningNumbers);

                final int count = countMatchNumbers(numbers, winningNumbers);
                return calculatePrizeByCount(count);
            }

            private void validateNumbers(final List<Integer> lottoNumbers) {
                for (int number : lottoNumbers) {
                    validateNumber(number);
                }
                if (new HashSet<>(lottoNumbers).size() != 6) {
                    throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
                }
            }

            private void validateNumber(final Integer lottoNumber) {
                if (lottoNumber < 1 || lottoNumber > 45) {
                    throw new IllegalArgumentException("로또 번호는 1부터 45까지의 숫자여야 합니다.");
                }
            }

            private int countMatchNumbers(
                    final List<Integer> numbers,
                    final List<Integer> winningNumbers
            ) {
                int count = 0;
                for (int number : numbers) {
                    for (int winningNumber : winningNumbers) {
                        if (number == winningNumber) {
                            count++;
                        }
                    }
                }

                return count;
            }

            private int calculatePrizeByCount(final int count) {
                return switch (count) {
                    case 6 -> 1_000_000_000;
                    case 5 -> 50_000_000;
                    case 4 -> 500_000;
                    case 3 -> 5_000;
                    default -> 0;
                };
            }
        }

        final var lottoGame = new LottoGame();

        assertAll(
                () -> assertThat(lottoGame.calculatePrize(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6))).isEqualTo(1_000_000_000),
                () -> assertThat(lottoGame.calculatePrize(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 7))).isEqualTo(50_000_000),
                () -> assertThat(lottoGame.calculatePrize(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 7, 8))).isEqualTo(500_000),
                () -> assertThat(lottoGame.calculatePrize(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 7, 8, 9))).isEqualTo(5_000),
                () -> assertThat(lottoGame.calculatePrize(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 7, 8, 9, 10))).isEqualTo(0)
        );
    }

    /**
     * 메서드를 작게 만들어 추상화한 코드입니다.
     * 메서드가 한 가지 일만 하도록 작성하면 코드를 이해하기 쉬워집니다.
     * 추상화 수준을 적절하게 유지하면 유지보수성을 높일 수 있습니다.
     * 메서드를 작게 만들어 추상화하다 보면 메서드의 역할이 명확해지지만, 객체의 역할은 아직 명확하지 않습니다.
     * 어떻게 추상화하여 객체의 역할을 명확하게 드러낼 수 있을까?
     */
    @Test
    @DisplayName("어떻게 추상화하여 객체의 역할을 명확하게 드러낼 수 있을까?")
    void 어떻게_추상화하여_객체의_역할을_명확하게_드러낼_수_있을까() {
        class LottoNumber {
            private final int value;

            public LottoNumber(final int value) {
                if (value < 1 || value > 45) {
                    throw new IllegalArgumentException("로또 번호는 1부터 45까지의 숫자여야 합니다.");
                }
                this.value = value;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                LottoNumber that = (LottoNumber) o;
                return value == that.value;
            }

            @Override
            public int hashCode() {
                return Objects.hash(value);
            }
        }

        class Lotto {
            private final Set<LottoNumber> numbers;

            public Lotto(final List<Integer> numbers) {
                this(numbers.stream()
                        .map(LottoNumber::new)
                        .collect(toSet()));
            }

            public Lotto(final Set<LottoNumber> numbers) {
                if (numbers.size() != 6) {
                    throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
                }

                this.numbers = numbers;
            }

            int countMatchNumbers(final Lotto winningLotto) {
                int count = 0;
                for (LottoNumber number : numbers) {
                    for (LottoNumber winningNumber : winningLotto.numbers) {
                        if (number.equals(winningNumber)) {
                            count++;
                        }
                    }
                }

                return count;
            }
        }

        enum LottoRank {
            FIRST(6, 1_000_000_000),
            SECOND(5, 50_000_000),
            THIRD(4, 500_000),
            FOURTH(3, 5_000),
            NONE(0, 0);

            private final int count;
            private final int prize;

            LottoRank(final int count, final int prize) {
                this.count = count;
                this.prize = prize;
            }

            public static LottoRank of(final int count) {
                return Arrays.stream(values())
                        .filter(prize -> prize.count == count)
                        .findFirst()
                        .orElse(NONE);
            }

            public int getPrize() {
                return prize;
            }
        }

        class LottoGame {
            int calculatePrize(
                    final List<Integer> numbers,
                    final List<Integer> winningNumbers
            ) {
                return calculatePrize(new Lotto(numbers), new Lotto(winningNumbers));
            }

            int calculatePrize(
                    final Lotto lotto,
                    final Lotto winningLotto
            ) {
                final var count = lotto.countMatchNumbers(winningLotto);
                final var lottoRank = LottoRank.of(count);
                return lottoRank.getPrize();
            }
        }

        final var lottoGame = new LottoGame();

        assertAll(
                () -> assertThat(lottoGame.calculatePrize(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6))).isEqualTo(1_000_000_000),
                () -> assertThat(lottoGame.calculatePrize(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 7))).isEqualTo(50_000_000),
                () -> assertThat(lottoGame.calculatePrize(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 7, 8))).isEqualTo(500_000),
                () -> assertThat(lottoGame.calculatePrize(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 7, 8, 9))).isEqualTo(5_000),
                () -> assertThat(lottoGame.calculatePrize(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 7, 8, 9, 10))).isEqualTo(0)
        );
    }

    /**
     * 객체의 역할을 명확하게 드러낸 코드입니다.
     * 객체가 자기 자신의 역할을 명확하게 드러내면 코드를 읽는 사람이 코드의 의도를 파악하기 쉬워집니다.
     * 코드를 읽는 사람이 코드의 의도를 파악하기 쉽게 만들기 위해 객체의 역할을 명확하게 드러내는 것이 중요합니다.
     */
    @Test
    @DisplayName("객체의 역할을 명확하게 드러낸 코드")
    void 객체의_역할을_명확하게_드러낸_코드() {
        class LottoNumber {
            private final int value;

            public LottoNumber(final int value) {
                if (value < 1 || value > 45) {
                    throw new IllegalArgumentException("로또 번호는 1부터 45까지의 숫자여야 합니다.");
                }
                this.value = value;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                LottoNumber that = (LottoNumber) o;
                return value == that.value;
            }

            @Override
            public int hashCode() {
                return Objects.hash(value);
            }
        }

        class Lotto {
            private final Set<LottoNumber> numbers;

            public Lotto(final List<Integer> numbers) {
                this(numbers.stream()
                        .map(LottoNumber::new)
                        .collect(toSet()));
            }

            public Lotto(final Set<LottoNumber> numbers) {
                if (numbers.size() != 6) {
                    throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
                }

                this.numbers = numbers;
            }

            int countMatchNumbers(final Lotto winningLotto) {
                int count = 0;
                for (LottoNumber number : numbers) {
                    for (LottoNumber winningNumber : winningLotto.numbers) {
                        if (number.equals(winningNumber)) {
                            count++;
                        }
                    }
                }

                return count;
            }
        }

        enum LottoRank {
            FIRST(6, 1_000_000_000),
            SECOND(5, 50_000_000),
            THIRD(4, 500_000),
            FOURTH(3, 5_000),
            NONE(0, 0);

            private final int count;
            private final int prize;

            LottoRank(final int count, final int prize) {
                this.count = count;
                this.prize = prize;
            }

            public static LottoRank of(final int count) {
                return Arrays.stream(values())
                        .filter(prize -> prize.count == count)
                        .findFirst()
                        .orElse(NONE);
            }

            public int getPrize() {
                return prize;
            }
        }

        class LottoGame {
            int calculatePrize(
                    final List<Integer> numbers,
                    final List<Integer> winningNumbers
            ) {
                return calculatePrize(new Lotto(numbers), new Lotto(winningNumbers));
            }

            int calculatePrize(
                    final Lotto lotto,
                    final Lotto winningLotto
            ) {
                final var count = lotto.countMatchNumbers(winningLotto);
                final var lottoRank = LottoRank.of(count);
                return lottoRank.getPrize();
            }
        }

        final var lottoGame = new LottoGame();

        assertAll(
                () -> assertThat(lottoGame.calculatePrize(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6))).isEqualTo(1_000_000_000),
                () -> assertThat(lottoGame.calculatePrize(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 7))).isEqualTo(50_000_000),
                () -> assertThat(lottoGame.calculatePrize(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 7, 8))).isEqualTo(500_000),
                () -> assertThat(lottoGame.calculatePrize(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 7, 8, 9))).isEqualTo(5_000),
                () -> assertThat(lottoGame.calculatePrize(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 7, 8, 9, 10))).isEqualTo(0)
        );
    }

    /**
     * 메서드의 이름을 잘 지어도 매개변수가 무엇이고 어떤 역할을 하는지 알기 어렵다면 의미를 전달하기 어렵습니다.
     * 매개변수 또한 의미를 전달할 수 있도록 작성하는 것이 중요합니다.
     * 아래 코드는 좋아하는 음식과 싫어하는 음식을 가지고 있는 Crew 객체를 생성하는 코드입니다.
     * 클래스 내에 이름을 잘 지어두었다면 의미를 전달할 수 있지만, 매번 해당 클래스로 가서 이름을 확인하는 방법은 번거롭습니다.
     * 어떻게 매개변수의 의미를 전달할 수 있을까?
     */
    @Test
    @DisplayName("어떻게 매개변수의 의미를 전달할 수 있을까?")
    void 어떻게_매개변수의_의미를_전달할_수_있을까() {
        final var crew = new Crew(
                /*name*/ "Neo",
                /*likeMenuItems*/ Set.of("쌈밥", "김치찌개", "탕수육", "비빔밥"),
                /*dislikeMenuItems*/ Set.of("샐러드", "파인애플 볶음밥", "미소시루", "하이라이스")
        );

        assertThat(crew.name()).isEqualTo("Neo");
    }

    /**
     * 주석으로 매개변수의 의미를 전달할 수 있는 코드입니다.
     * 하지만 주석을 신경쓰지 않고 코드를 변경하거나, 처음부터 주석과 다른 코드를 작성했다면 오히려 오해할 수 있는 코드가 될 수 있습니다.
     * 주석을 사용하지 않고 매개변수의 의미를 전달할 수 있는 방법은 없을까?
     */
    @Test
    @DisplayName("주석을 사용하지 않고 매개변수의 의미를 전달할 수 있는 방법은 없을까?")
    void 주석을_사용하지_않고_매개변수의_의미를_전달할_수_있는_방법은_없을까() {
        /* Note: 자바는 네임드 파라미터를 지원하지 않는다. IntelliJ에서 도움을 주지만 아쉬운 부분이 있다.
        final var neo = new Crew(
          name: "neo",
          likeMenuItems: Set.of("쌈밥", "김치찌개", "탕수육", "비빔밥"),
          dislikeMenuItems: Set.of("샐러드", "파인애플 볶음밥", "미소시루", "하이라이스")
        );
         */

        class Builder {
            private String name;
            private Set<String> likeMenuItems;
            private Set<String> dislikeMenuItems;

            public Builder name(final String name) {
                this.name = name;
                return this;
            }

            public Builder likeMenuItems(final Set<String> likeMenuItems) {
                this.likeMenuItems = likeMenuItems;
                return this;
            }

            public Builder dislikeMenuItems(final Set<String> dislikeMenuItems) {
                this.dislikeMenuItems = dislikeMenuItems;
                return this;
            }

            public Crew build() {
                return new Crew(name, likeMenuItems, dislikeMenuItems);
            }
        }

        final var crew = new Builder()
                .name("Neo")
                .dislikeMenuItems(Set.of("샐러드", "파인애플 볶음밥", "미소시루", "하이라이스"))
                .likeMenuItems(Set.of("쌈밥", "김치찌개", "탕수육", "비빔밥"))
                .build();

        assertThat(crew.name()).isEqualTo("Neo");
    }

    /**
     * 빌더를 통해 매개변수의 의미를 전달할 수 있는 코드입니다.
     * 빌더를 사용하면 매개변수의 의미를 전달할 수 있고, 빌더를 통해 객체를 생성할 때 매개변수의 순서를 신경쓰지 않아도 됩니다.
     * 빌더를 사용하면 객체를 생성할 때 매개변수의 순서를 신경쓰지 않아도 되기 때문에 객체를 생성할 때 매개변수의 순서를 신경쓰지 않아도 됩니다.
     * 매개변수가 많아지면 어떤 값을 설정했는지 확인이 어렵거나 어떤 매개변수끼리 의미가 있는지 확인이 어려워질 수 있습니다.
     * 매개변수를 묶어 의미를 전달할 수 있는 방법은 없을까?
     */
    @Test
    @DisplayName("매개변수를 묶어 의미를 전달할 수 있는 방법은 없을까?")
    void 매개변수를_묶어_의미를_전달할_수_있는_방법은_없을까() {
        record Taste(
                Set<String> likeMenuItems,
                Set<String> dislikeMenuItems
        ) {
            static class Builder {
                private Set<String> likeMenuItems;
                private Set<String> dislikeMenuItems;

                public Builder likeMenuItems(final String... likeMenuItems) {
                    return likeMenuItems(Set.of(likeMenuItems));
                }

                public Builder likeMenuItems(final Set<String> likeMenuItems) {
                    this.likeMenuItems = likeMenuItems;
                    return this;
                }

                public Builder dislikeMenuItems(final String... dislikeMenuItems) {
                    return dislikeMenuItems(Set.of(dislikeMenuItems));
                }

                public Builder dislikeMenuItems(final Set<String> dislikeMenuItems) {
                    this.dislikeMenuItems = dislikeMenuItems;
                    return this;
                }

                public Taste build() {
                    return new Taste(likeMenuItems, dislikeMenuItems);
                }
            }
        }

        record Crew(
                String name,
                Taste taste
        ) {
            static class Builder {
                private String name;
                private Taste taste;

                public Builder name(final String name) {
                    this.name = name;
                    return this;
                }

                public Builder taste(final Taste taste) {
                    this.taste = taste;
                    return this;
                }

                public Crew build() {
                    return new Crew(name, taste);
                }
            }
        }

        /* Note: 만약 타입이 같은 매개변수의 초기화 순서가 바뀐다면 문제가 발생할 수 있다.
        final var crew = new Crew(
                "Neo",
                Set.of("쌈밥", "김치찌개", "탕수육", "비빔밥"),
                Set.of("샐러드", "파인애플 볶음밥", "미소시루", "하이라이스")
        );
         */

        final var taste = new Taste.Builder()
                .likeMenuItems("쌈밥", "김치찌개", "탕수육", "비빔밥")
                .dislikeMenuItems("샐러드", "파인애플 볶음밥", "미소시루", "하이라이스")
                .build();
        final var crew = new Crew.Builder()
                .name("Neo")
                .taste(taste)
                .build();

        assertThat(crew.name()).isEqualTo("Neo");
    }

    /**
     * 매개변수를 묶어 의미를 전달할 수 있는 코드입니다.
     * 의미가 동일한 매개변수를 묶어 별도 클래스로 분리하여 객체의 역할을 명확하게 드러내면 코드를 읽는 사람이 코드의 의도를 파악하기 쉬워집니다.
     * 원리는 위에서 학습한 메서드 분리, 추상화, 객체의 역할을 명확하게 드러내는 방법과 동일합니다.
     * 의미가 명확해진 장점은 있지만, 객체가 많아지면 유지보수성이 떨어질 수 있습니다.
     * 정답은 없습니다. 적절한 추상화 수준을 유지하는 것이 중요합니다.
     */
    @Test
    @DisplayName("의미가 명확해진 장점은 있지만, 객체가 많아지면 유지보수성이 떨어질 수 있으니 적절한 추상화 수준을 유지하는 것이 중요합니다")
    void 의미가_명확해진_장점은_있지만_객체가_많아지면_유지보수성이_떨어질_수_있으니_적절한_추상화_수준을_유지하는_것이_중요합니다() {
        record Taste(
                Set<String> likeMenuItems,
                Set<String> dislikeMenuItems
        ) {
            static class Builder {
                private Set<String> likeMenuItems;
                private Set<String> dislikeMenuItems;

                public Builder likeMenuItems(final String... likeMenuItems) {
                    return likeMenuItems(Set.of(likeMenuItems));
                }

                public Builder likeMenuItems(final Set<String> likeMenuItems) {
                    this.likeMenuItems = likeMenuItems;
                    return this;
                }

                public Builder dislikeMenuItems(final String... dislikeMenuItems) {
                    return dislikeMenuItems(Set.of(dislikeMenuItems));
                }

                public Builder dislikeMenuItems(final Set<String> dislikeMenuItems) {
                    this.dislikeMenuItems = dislikeMenuItems;
                    return this;
                }

                public Taste build() {
                    return new Taste(likeMenuItems, dislikeMenuItems);
                }
            }
        }

        record Crew(
                String name,
                Taste taste
        ) {
            static class Builder {
                private String name;
                private Taste taste;

                public Builder name(final String name) {
                    this.name = name;
                    return this;
                }

                public Builder taste(final Taste taste) {
                    this.taste = taste;
                    return this;
                }

                public Crew build() {
                    return new Crew(name, taste);
                }
            }
        }

        final var taste = new Taste.Builder()
                .likeMenuItems("쌈밥", "김치찌개", "탕수육", "비빔밥")
                .dislikeMenuItems("샐러드", "파인애플 볶음밥", "미소시루", "하이라이스")
                .build();
        final var crew = new Crew.Builder()
                .name("Neo")
                .taste(taste)
                .build();

        assertThat(crew.name()).isEqualTo("Neo");
    }

    /**
     * 기능을 구현하다 보면 이미 누군가가 구현한 기능을 재사용하고 싶을 때가 있습니다.
     * 대표적으로 자바에서 제공하는 Collection API를 사용하는 것입니다.
     * Collection API를 사용하면 코드를 재사용할 수 있고, 코드를 읽는 사람이 코드의 의도를 파악하기 쉬워집니다.
     * Collection API를 사용하여 코드를 재사용하고 의도를 파악하기 쉽게 만들 수 없을까?
     */
    @Test
    @DisplayName("Collection API를 사용하여 코드를 재사용하고 의도를 파악하기 쉽게 만들 수 없을까?")
    void Collection_API를_사용하여_코드를_재사용하고_의도를_파악하기_쉽게_만들_수_없을까() {
        class Menu {
            private final List<String> menuItems;

            public Menu(final List<String> menuItems) {
                if (menuItems.size() != menuItems.stream().distinct().count()) {
                    throw new IllegalArgumentException("중복된 메뉴가 있습니다.");
                }

                this.menuItems = menuItems;
            }
        }

        assertThatThrownBy(() -> {
            new Menu(List.of("쌈밥", "김치찌개", "쌈밥", "비빔밥"));
        }).hasMessage("중복된 메뉴가 있습니다.");
    }

    /**
     * Collection의 distinct를 사용하여 코드를 재사용하고 의도를 파악하기 쉽게 만든 코드입니다.
     * Collection API를 사용하면 코드를 재사용할 수 있고, 코드를 읽는 사람이 코드의 의도를 파악하기 쉬워집니다.
     * 위 객체 분리에서 학습한 것처럼 메서드로 나타내는 것 보다 객체 자체로 나타내는 것이 더 좋은 방법일 수 있습니다.
     * 객체 자체로 의미를 전달할 수 있는 방법은 없을까?
     */
    @Test
    @DisplayName("객체 자체로 의미를 전달할 수 있는 방법은 없을까?")
    void 객체_자체로_의미를_전달할_수_있는_방법은_없을까() {
        class Menu {
            private final Set<String> menuItems;

            public Menu(final Set<String> menuItems) {
                this.menuItems = menuItems;
            }
        }

        assertThatCode(() -> {
            new Menu(Set.of("쌈밥", "김치찌개", "비빔밥"));
        }).doesNotThrowAnyException();
    }

    /**
     * 객체 자체로 의미를 전달할 수 있는 코드입니다.
     * 자료구조를 활용하면 코드를 읽는 사람이 코드의 의도를 파악하기 쉬워집니다.
     * Set 자료구조는 중복을 허용하지 않기 때문에 해당 객체를 생성하는 시점에 중복이 없는 것을 보장할 수 있습니다.
     * 적절한 API를 사용하면 견고한 코드를 작성할 수 있습니다.
     */
    @Test
    @DisplayName("적절한 API를 사용하면 견고한 코드를 작성할 수 있습니다")
    void 적절한_API를_사용하면_견고한_코드를_작성할_수_있습니다() {
        class Menu {
            private final Set<String> menuItems;

            public Menu(final Set<String> menuItems) {
                this.menuItems = menuItems;
            }
        }

        assertThatCode(() -> {
            // Note: List 자료구조를 허용하지 않고 Set 자료구조만 허용하여 중복을 허용하지 않는다는 의도를 전달할 수 있다.
            // new Menu(Set.of("쌈밥", "김치찌개", "쌈밥", "비빔밥"));

            new Menu(Set.of("쌈밥", "김치찌개", "비빔밥"));
        }).doesNotThrowAnyException();
    }

    /**
     * 적절한 API를 사용하는 것이 견고한 코드를 작성할 수 있다는 사실을 알게 되었습니다.
     * 그렇다고 너무 API를 사용하는 것에 매몰되면 부작용이 발생할 수 있습니다.
     */
    @Test
    @DisplayName("API에 매몰되어 과하게 사용하면 생길 수 있는 문제")
    void API에_매몰되어_과하게_사용하면_생길_수_있는_문제() {
        class Menu {
            private final Map<String, Integer> menu;

            public Menu(final Map<String, Integer> menu) {
                this.menu = menu;
            }

            public int getPrice(final String menuName) {
                return menu.getOrDefault(menuName, 0);
            }
        }
        final var menu = new Menu(Map.of(
                "쌈밥", 11_000,
                "김치찌개", 9_000,
                "비빔밥", 10_000,
                "평양냉면", 15_000
        ));

        final int 평양냉면_가격 = menu.getPrice("평양냉면");

        assertThat(평양냉면_가격).isEqualTo(15_000);
    }
}
