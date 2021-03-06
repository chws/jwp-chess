package wooteco.chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import wooteco.chess.exception.CommandException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {
    @DisplayName("유효하지 않는 명령어 입력시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"hello", "star!"})
    void ofTest(String input) {
        assertThatThrownBy(() -> Command.beforeGameCommandOf(input)).isInstanceOf(CommandException.class)
                .hasMessage("잘못된 명령어를 입력하셨습니다.");
        assertThatThrownBy(() -> Command.inGameCommandOf(input)).isInstanceOf(CommandException.class)
                .hasMessage("잘못된 명령어를 입력하셨습니다.");
    }
}