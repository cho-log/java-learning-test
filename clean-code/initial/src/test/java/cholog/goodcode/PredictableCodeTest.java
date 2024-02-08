package cholog.goodcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * 좋은 코드의 기준은 사람마다 다르지만 대부분의 사람들이 동의하는 몇 가지 기준이 있습니다.
 * 그 기준 중 유지보수성과 확장성은 매우 중요한 기준입니다.
 * 예측 가능한 코드는 유지보수성을 높이고 버그를 줄이는데 도움을 줍니다.
 * 유지보수성과 확장성을 위한 예측 가능한 코드를 작성하는 방법을 알아봅니다.
 */
public class PredictableCodeTest {
    record Car(int position) {
    }

    /**
     * 아래 코드는 자동차 경주에서 평균 위치를 계산하는 기능입니다.
     * 게임 참여자가 없을 경우 -1을 반환하여 처리하면 해당 기능을 사용하는 개발자들은 매번 -1을 체크해야 하고, 이는 실수하기 좋은 코드가 됩니다.
     * 어떻게 매번 -1를 체크하지 않도록 할 수 있을까?
     */
    @Test
    @DisplayName("어떻게 매번 -1를 체크하지 않도록 할 수 있을까?")
    void 어떻게_매번_값을_체크하지_않도록_할_수_있을까() {
        // TODO: 매번 -1을 체크하지 않고 참여자가 없다는 것을 명시적으로 표현할 수 있는 코드를 작성해보세요.
        class RacingGame {
            private static final int NO_PARTICIPANT = -1;

            private final List<Car> participants;

            RacingGame() {
                this(List.of());
            }

            RacingGame(final List<Car> participants) {
                this.participants = participants;
            }

            int averagePosition() {
                // Note: 매직값은 버그를 유발할 수 있다.
                return (int) participants.stream()
                        .mapToInt(Car::position)
                        .average()
                        .orElse(NO_PARTICIPANT);
            }
        }

        final var racingGame = new RacingGame();

        final var averagePosition = racingGame.averagePosition();

        assertThat(averagePosition).isEqualTo(RacingGame.NO_PARTICIPANT);
    }

    /**
     * null을 통해 의도를 전달하는 방법입니다.
     * null을 사용하면 코드를 읽는 사람이 해당 변수가 null일 수 있다는 것을 알 수 있습니다.
     * 하지만 null을 사용하면 NullPointerException이 발생할 수 있고, null로 인해 생길 수 있는 부작용이 발생할 수 있습니다.
     * null을 사용하지 않고 의도를 전달하는 방법은 무엇일까?
     */
    @Test
    @DisplayName("null을 사용하지 않고 의도를 전달하는 방법은 무엇일까?")
    void null을_사용하지_않고_의도를_전달하는_방법은_무엇일까() {
        // TODO: null을 사용하지 않고 참여자가 없다는 것을 명시적으로 표현할 수 있는 코드를 작성해보세요.
        class RacingGame {
            private final List<Car> participants;

            RacingGame() {
                this(List.of());
            }

            RacingGame(final List<Car> participants) {
                this.participants = participants;
            }

            Integer averagePosition() {
                final OptionalDouble average = participants.stream()
                        .mapToInt(Car::position)
                        .average();

                if (average.isEmpty()) {
                    // Note: null은 버그를 유발할 수 있다.
                    return null;
                }
                return (int) average.getAsDouble();
            }
        }

        final var racingGame = new RacingGame();

        final var averagePosition = racingGame.averagePosition();

        assertThat(averagePosition).isNull();
    }

    /**
     * Optional을 통해 의도를 전달하는 방법입니다.
     * Optional을 사용하면 코드를 읽는 사람이 해당 변수가 비어있을 수 있다는 것을 알 수 있습니다.
     * 지금의 구조는 참여자가 없다는 사실을 전달할 수 있지만, 그 처리를 외부에 위임하고 있습니다.
     * 만약 참여자가 없다는 사실을 처리하는 코드가 여러군데에 중복되어 있다면, 이는 유지보수성을 떨어뜨리는 코드가 됩니다.
     * 어떻게 참여자가 없는 상황을 처리하는 코드를 중복하지 않고 처리할 수 있을까?
     */
    @Test
    @DisplayName("어떻게 참여자가 없는 상황을 처리하는 코드를 중복하지 않고 처리할 수 있을까?")
    void 어떻게_참여자가_없는_상황을_처리하는_코드를_중복하지_않고_처리할_수_있을까() {
        // TODO: 참여자가 없는 상황을 중복하지 않고 처리할 수 있는 코드를 작성해보세요.
        class RacingGame {
            private final List<Car> participants;

            RacingGame() {
                this(List.of());
            }

            RacingGame(final List<Car> participants) {
                this.participants = participants;
            }

            // Note: Optional를 사용하면 외부에 처리를 위임하게 되고, 응집도가 떨어질 수 있다.
            Optional<Integer> averagePosition() {
                final OptionalDouble average = participants.stream()
                        .mapToInt(Car::position)
                        .average();

                if (average.isEmpty()) {
                    return Optional.empty();
                }
                return Optional.of((int) average.getAsDouble());
            }
        }

        final var racingGame = new RacingGame();

        final var averagePosition = racingGame.averagePosition();

        assertThat(averagePosition).isEmpty();
    }

    /**
     * 예외를 발생하여 명시적으로 처리하는 방법입니다.
     * 논리적으로 참여자가 없다는 사실을 처리하는 코드를 중복하지 않고 처리할 수 있습니다.
     * 설계에 따라 외부에서 처리하는 것이 적합할 경우 Optional을 사용할 수 있지만, 설계에 따라 예외를 발생하는 것이 적합할 수 있습니다.
     * 정답은 없습니다. 상황에 따라 적절한 방법을 선택해야 합니다.
     */
    @Test
    @DisplayName("예외를 발생하여 명시적으로 처리하는 방법입니다.")
    void 예외를_발생하여_명시적으로_처리하는_방법입니다() {
        class RacingGame {
            private final List<Car> participants;

            RacingGame() {
                this(List.of());
            }

            RacingGame(final List<Car> participants) {
                this.participants = participants;
            }

            // Note: 내부에서 예외를 처리하면 확장성이 떨어질 수 있다.
            int averagePosition() {
                final OptionalDouble average = participants.stream()
                        .mapToInt(Car::position)
                        .average();

                if (average.isEmpty()) {
                    throw new IllegalStateException("게임 참여자가 없습니다.");
                }
                return (int) average.getAsDouble();
            }
        }

        final var racingGame = new RacingGame();

        assertThatThrownBy(() -> {
            racingGame.averagePosition();
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("게임 참여자가 없습니다.");
    }

    /**
     * 아래 코드는 4 이상의 파워가 넘어왔을 때 자동차를 움직이는 기능입니다.
     * 자동차의 위치를 조회하는 것 또한 해당 메서드를 통해 조회하고 있습니다.
     * 자동차 이동과 조회를 같이 할 경우 어떠한 문제가 있을지 고민 후 개선해보세요.
     */
    @Test
    @DisplayName("자동차 이동과 조회를 같이 할 경우 어떠한 문제가 있을지 고민 후 개선한다.")
    void 자동차_이동과_조회를_같이_할_경우_어떠한_문제가_있을지_고민_후_개선한다() {
        class Car {
            private int position;

            // TODO: 자동차 이동과 조회를 같이 할 경우 어떠한 문제가 있을지 고민 후 개선해보세요.
            int move(final int power) {
                if (power <= 4) {
                    return position;
                }

                return ++position;
            }
        }

        final var car = new Car();

        final var position = car.move(5);

        assertThat(position).isEqualTo(1);
    }

    /**
     * 아래 코드는 자동차가 최대 5칸을 움직일 수 있는 코드입니다.
     * 5칸에 위치하였을 때 더 이동하려고 하면 더 이상 움직이지 않고 위치를 유지하고 있습니다.
     * 아래 자동차가 최대 위치에서 움직이지 않고 유지하는 코드는 어떠한 문제가 있을지 고민 후 개선해보세요.
     */
    @Test
    @DisplayName("자동차가 최대 위치에서 움직이지 않고 유지하는 코드는 어떠한 문제가 있을지 고민 후 개선한다.")
    void 자동차가_최대_위치에서_움직이지_않고_유지하는_코드는_어떠한_문제가_있을지_고민_후_개선한다() {
        class Car {
            private int position;

            Car(final int position) {
                this.position = position;
            }

            // TODO: 자동차가 최대 위치에서 움직이지 않고 유지하는 코드는 어떠한 문제가 있을지 고민 후 개선해보세요.
            void move(final int power) {
                if (power <= 4) {
                    return;
                }
                if (position >= 5) {
                    return;
                }

                position++;
            }

            int getPosition() {
                return position;
            }
        }

        final var car = new Car(5);

        car.move(5);

        assertThat(car.getPosition()).isEqualTo(5);
    }

    /**
     * 더 이상 움직일 수 없을 때 예외를 발생하여 명시적으로 처리하는 방법입니다.
     * 중요한 동작을 무시하는 것은 버그를 유발할 수 있습니다.
     * 하지만 아직 파워가 4보다 작을 때는 무시하고 있습니다.
     * 파워가 4보다 작을 때 무시하는 코드는 어떠한 문제가 있을지 고민 후 개선해보세요.
     */
    @Test
    @DisplayName("파워가 4보다 작을 때 무시하는 코드는 어떠한 문제가 있을지 고민 후 개선한다.")
    void 파워가_4보다_작을_때_무시하는_코드는_어떠한_문제가_있을지_고민_후_개선한다() {
        class Car {
            private int position;

            Car(final int position) {
                this.position = position;
            }

            void move(final int power) {
                // TODO: 파워가 4보다 작을 때 무시하는 코드는 어떠한 문제가 있을지 고민 후 개선해보세요.
                if (power <= 4) {
                    return;
                }
                if (position >= 5) {
                    // Note: 중요한 동작을 무시하는 것은 버그를 유발할 수 있다.
                    throw new IllegalStateException("더 이상 움직일 수 없습니다.");
                }

                position++;
            }
        }

        final var car = new Car(5);

        assertThatThrownBy(() -> {
            car.move(5);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("더 이상 움직일 수 없습니다.");
    }

    /**
     * 아래 코드는 입력된 문자열 명령에 따라 동작하는 코드입니다.
     * 문자열로 명령을 받는 것은 어떠한 문제가 있을지 고민 후 개선해보세요.
     */
    @Test
    @DisplayName("문자열로 명령을 받는 것은 어떠한 문제가 있을지 고민 후 개선한다.")
    void 문자열로_명령을_받는_것은_어떠한_문제가_있을지_고민_후_개선한다() {
        class Calculator {
            private static final String PLUS = "PLUS";
            private static final String MINUS = "MINUS";

            // TODO: 문자열로 명령을 받는 것은 어떠한 문제가 있을지 고민 후 개선해보세요.
            public static int calculate(
                    final String command,
                    final int left,
                    final int right
            ) {
                if (PLUS.equals(command)) {
                    return left + right;
                }
                if (MINUS.equals(command)) {
                    return left - right;
                }

                throw new UnsupportedOperationException("지원하지 않는 명령입니다.");
            }
        }

        assertAll(
                () -> assertThat(Calculator.calculate("PLUS", 1, 2)).isEqualTo(3),
                () -> assertThat(Calculator.calculate("MINUS", 1, 2)).isEqualTo(-1)
        );
    }

    /**
     * 명령을 문자열에서 열거형으로 변경하여 명시적으로 처리하는 방법입니다.
     * 문자열 상수의 경우 타입 안정성이 보장되지 않아 버그를 유발할 수 있습니다.
     * 열거형으로 변경하면 타입 안정성이 보장되어 버그를 줄일 수 있습니다.
     * 하지만 새로운 열거형이 추가되었을 때 해당 명령을 처리하는 코드를 놓친다면 버그를 유발할 수 있습니다.
     * 어떻게 새로운 열거형이 추가되었을 때 해당 명령을 처리하는 코드를 놓치지 않을 수 있을까?
     */
    @Test
    @DisplayName("어떻게 새로운 열거형이 추가되었을 때 해당 명령을 처리하는 코드를 놓치지 않을 수 있을까?")
    void 어떻게_새로운_열거형이_추가되었을_때_해당_명령을_처리하는_코드를_놓치지_않을_수_있을까() {
        enum Command {
            PLUS,
            MINUS,
            MULTIPLY
        }

        class Calculator {
            public static int calculate(
                    final Command command,
                    final int left,
                    final int right
            ) {
                if (command == Command.PLUS) {
                    return left + right;
                }
                if (command == Command.MINUS) {
                    return left - right;
                }
                // Note: MULTIPLY 명령이 추가되었지만 해당 명령을 처리하는 코드를 놓쳤습니다.

                throw new UnsupportedOperationException("지원하지 않는 명령입니다.");
            }
        }

        // TODO: 새로운 열거형이 추가되었을 때 해당 명령을 처리하는 코드를 놓치지 않을 수 있는 방법을 고민 후 개선해보세요.

        assertAll(
                () -> assertThat(Calculator.calculate(Command.PLUS, 1, 2)).isEqualTo(3),
                () -> assertThat(Calculator.calculate(Command.MINUS, 1, 2)).isEqualTo(-1)
        );
    }

    /**
     * 테스트 코드를 추가하여 새로운 열거형이 추가되었을 때 해당 명령을 처리하는 코드를 놓치지 않는 방법입니다.
     * 새로운 열거형이 추가되었을 때 해당 명령을 처리하는 코드를 놓치지 않으려면 테스트 코드를 작성하여 해당 명령을 처리하는 코드를 놓치지 않도록 해야 합니다.
     * 하지만 테스트를 직접 실행시키기 전까지 해당 명령을 처리하는 코드를 놓칠 수 있습니다.
     * 어떻게 더 빠른 시점에 해당 명령을 처리하는 코드를 놓치지 않을 수 있을까?
     */
    @Test
    @DisplayName("어떻게 더 빠른 시점에 해당 명령을 처리하는 코드를 놓치지 않을 수 있을까?")
    void 어떻게_더_빠른_시점에_해당_명령을_처리하는_코드를_놓치지_않을_수_있을까() {
        enum Command {
            PLUS,
            MINUS,
            MULTIPLY
        }

        class Calculator {
            public static int calculate(
                    final Command command,
                    final int left,
                    final int right
            ) {
                if (command == Command.PLUS) {
                    return left + right;
                }
                if (command == Command.MINUS) {
                    return left - right;
                }

                throw new UnsupportedOperationException("지원하지 않는 명령입니다.");
            }
        }

        // TODO: 더 빠른 시점에 해당 명령을 처리하는 코드를 놓치지 않을 수 있는 방법을 고민 후 개선해보세요.

        for (final Command command : Command.values()) {
            // Note: 런타임 시점에 해당 명령을 처리하는 코드를 놓치지 않을 수 있다.
            assertThatCode(() -> {
                Calculator.calculate(command, 1, 2);
            }).doesNotThrowAnyException();
        }
    }

    /**
     * switch 문을 사용하여 명령을 처리하는 방법입니다.
     * 놓친 코드를 찾는 시점을 런타임이 아닌 컴파일 타임으로 변경하여 해당 명령을 처리하는 코드를 놓치지 않을 수 있습니다.
     * 코드를 작성할 때 최대한 런타임 시점에 발생할 수 있는 오류를 컴파일 타임으로 발생하도록 작성하는 것이 좋습니다.
     */
    @Test
    @DisplayName("코드를 작성할 때 최대한 런타임 시점에 발생할 수 있는 오류를 컴파일 타임으로 발생하도록 작성하는 것이 좋다.")
    void 코드를_작성할_때_최대한_런타임_시점에_발생할_수_있는_오류를_컴파일_타임으로_발생하도록_작성하는_것이_좋다() {
        enum Command {
            PLUS,
            MINUS,
            MULTIPLY
        }

        class Calculator {
            public static int calculate(
                    final Command command,
                    final int left,
                    final int right
            ) {
                return switch (command) {
                    case PLUS -> left + right;
                    case MINUS -> left - right;
                    case MULTIPLY -> left * right; // Note: 모든 열것값을 처리하지 않으면 컴파일 오류가 발생한다.
                };
            }
        }

        for (final Command command : Command.values()) {
            assertThatCode(() -> {
                Calculator.calculate(command, 1, 2);
            }).doesNotThrowAnyException();
        }
    }

    /**
     * 추가로 열거형도 객체로 바라보는 방법도 있습니다.
     * 이러한 방법은 열거형에 역할을 부여하여 열거형이 해당 역할을 수행하도록 하는 방법입니다.
     * 지금과 같이 간단한 코드에선 더욱 응집도가 높은 코드가 될 수 있지만, 열거형을 상수와 객체 역할을 모두 수행하도록 하는 것은 적절하지 않을 수 있습니다.
     * 열거형이 해당 역할을 수행하도록 하는 것이 적합한지 충분한 고민을 하고 사용해야 합니다.
     */
    @Test
    @DisplayName("열거형이 해당 역할을 수행하도록 하는 것이 적합한지 충분한 고민을 하고 사용해야 합니다.")
    void 열거형이_해당_역할을_수행하도록_하는_것이_적합한지_충분한_고민을_하고_사용해야_합니다() {
        // Note: 열거형을 상수로 바라볼 것인가, 객체로 바라볼 것인가에 대한 고민이 필요하다. 고민이 부족하면 부작용이 커진다.
        enum Command {
            PLUS((left, right) -> left + right),
            MINUS((left, right) -> left - right),
            MULTIPLY((left, right) -> left * right);

            private final BiFunction<Integer, Integer, Integer> function;

            Command(final BiFunction<Integer, Integer, Integer> function) {
                this.function = function;
            }

            int execute(int left, int right) {
                return function.apply(left, right);
            }
        }

        class Calculator {
            public static int calculate(
                    final Command command,
                    final int left,
                    final int right
            ) {
                return command.execute(left, right);
            }
        }

        for (final Command command : Command.values()) {
            assertThatCode(() -> {
                Calculator.calculate(command, 1, 2);
            }).doesNotThrowAnyException();
        }
    }
}
