import java.util.Random;

public class RPSPlayer extends RPSPlayerBase {
  // 勝ち数
  private int winCount = 0;
  // 負け数
  private int loseCount = 0;

  // 勝利数を返す
  public int getWinCount() {
    return winCount;
  }

  // 負け数を返す
  public int getLoseCount() {
    return loseCount;
  }

  /**
   * 出し手を返す
   */
  public RPSType go() {
    RPSType type = null;
    Random random = new Random();
    int rps = random.nextInt(3) + 1;

    switch (rps) {
      case 1:
        type = RPSType.ROCK;
        break;

      case 2:
        type = RPSType.SCISSORS;
        break;

      case 3:
        type = RPSType.PAPER;
        break;

      default:
        type = null;
        break;
    }

    return type;
  }

  /**
   * 勝敗を通知する
   */
  @Override
  public void onResult(boolean isWinner) {
    if (isWinner) {
      ++winCount;
    } else {
      ++loseCount;
    }
  }
}
