import java.util.Scanner;

public class RPSGame {
  // KEY event name
  private final static String INPUT_KEY_MANUAL = "m";
  private final static String INPUT_KEY_AUTO   = "a";
  private final static String INPUT_KEY_END    = "e";

  /**
   * メイン
   * @param args
   */
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    // キーボードからの入力を受け付ける
    boolean isLoop = true;
    while (isLoop) {
      printMenu();
      String input = scan.next();
      switch (input) {
        // マニュアルじゃんけん
        case INPUT_KEY_MANUAL:
          manual();
          break;

        // オートじゃんけん
        case INPUT_KEY_AUTO:
          auto();
          break;

        // 終了
        case INPUT_KEY_END:
          System.out.println("End");
          isLoop = false;
          break;

        default:
          System.out.println("もう一度入力してください。");
          break;
      }
    } ;
    scan.close();
  }

  /**
   * マニュアルモードの処理
   */
  private static void manual() {
    // 相手
    RPSPlayer rpsPlayer = new RPSPlayer();

    //自分の勝敗数
    int winCount = 0;
    int loseCount = 0;

    do {
      println("じゃんけん…");
      printCommand();
      Scanner scan = new Scanner(System.in);
      String input = scan.next();
      if (input.equals(INPUT_KEY_END)) {
        println("マニュアルじゃんけんを終了します");
        break;

      } else if (input.equals("1") || input.equals("2") || input.equals("3")) {
        //出し手のセット
        int pOne = Integer.parseInt(input);
        int pTwo = rpsPlayer.go().getId();

        // あいこ判定フラグ
        boolean isDraw = false;

        // 勝敗判定
        switch (pOne) {
          // グー
          case 1:
            if (pTwo == 1) {
              println("あなた：グー");
              println("相手：グー");
              isDraw = true;
            }
            if (pTwo == 2) {
              println("あなた：グー");
              println("相手：チョキ");
              winCount++;
              rpsPlayer.onResult(false);
              println("あなたの勝ち");
            }
            if (pTwo == 3) {
              println("あなた：グー");
              println("相手：パー");
              loseCount++;
              rpsPlayer.onResult(true);
              println("あなたの負け");
            }

            break;

          // チョキ
          case 2:
            if (pTwo == 1) {
              println("あなた：チョキ");
              println("相手：グー");
              loseCount++;
              rpsPlayer.onResult(true);
              println("あなたの負け");
            }
            if (pTwo == 2) {
              println("あなた：チョキ");
              println("相手：チョキ");
              isDraw = true;
            }
            if (pTwo == 3) {
              println("あなた：チョキ");
              println("相手：パー");
              winCount++;
              rpsPlayer.onResult(false);
              println("あなたの勝ち");
            }
            break;

          // パー
          case 3:
            if (pTwo == 1) {
              println("あなた：パー");
              println("相手：グー");
              winCount++;
              rpsPlayer.onResult(false);
              println("あなたの勝ち");
            }
            if (pTwo == 2) {
              println("あなた：パー");
              println("相手：チョキ");
              loseCount++;
              rpsPlayer.onResult(true);
              println("あなたの負け");
            }
            if (pTwo == 3) {
              println("あなた：パー");
              println("相手：パー");
              isDraw = true;
            }
            break;

          default:
            break;
        }

        // あいこの場合
        if (isDraw) {
          do {
            println("あいこで・・・");
            input = scan.next();

            if (input.equals("1") || input.equals("2") || input.equals("3")) {

              // 出し手をセットする
              pOne = Integer.parseInt(input);
              pTwo = rpsPlayer.go().getId();

              // 勝敗判定
              switch (pOne) {
                // グー
                case 1:
                  if (pTwo == 1) {
                    println("あなた：グー");
                    println("相手：グー");
                    isDraw = true;
                  }
                  if (pTwo == 2) {
                    println("あなた：グー");
                    println("相手：チョキ");
                    winCount++;
                    rpsPlayer.onResult(false);
                    println("あなたの勝ち");
                    isDraw = false;
                  }
                  if (pTwo == 3) {
                    println("あなた：グー");
                    println("相手：パー");
                    loseCount++;
                    rpsPlayer.onResult(true);
                    println("あなたの負け");
                    isDraw = false;
                  }

                  break;

                // チョキ
                case 2:
                  if (pTwo == 1) {
                    println("あなた：チョキ");
                    println("相手：グー");
                    loseCount++;
                    rpsPlayer.onResult(true);
                    println("あなたの負け");
                    isDraw = false;
                  }
                  if (pTwo == 2) {
                    println("あなた：チョキ");
                    println("相手：チョキ");
                    isDraw = true;
                  }
                  if (pTwo == 3) {
                    println("あなた：チョキ");
                    println("相手：パー");
                    winCount++;
                    rpsPlayer.onResult(false);
                    println("あなたの勝ち");
                    isDraw = false;
                  }
                  break;

                // パー
                case 3:
                  if (pTwo == 1) {
                    println("あなた：パー");
                    println("相手：グー");
                    winCount++;
                    rpsPlayer.onResult(false);
                    println("あなたの勝ち");
                    isDraw = false;
                  }
                  if (pTwo == 2) {
                    println("あなた：パー");
                    println("相手：チョキ");
                    loseCount++;
                    rpsPlayer.onResult(true);
                    println("あなたの負け");
                    isDraw = false;
                  }
                  if (pTwo == 3) {
                    println("あなた：パー");
                    println("相手：パー");
                    isDraw = true;
                  }
                  break;

                default:
                  break;
              }
            }
          } while (isDraw);
        }

        // 結果表示
        println("-----------------------------------------");
        println("対戦成績");
        println("自分：" + winCount + "勝");
        println("相手：" + rpsPlayer.getWinCount() + "勝");
        println("-----------------------------------------");
        println("");
      } else {
        println("Please input \"1\" or \"2\" or \"3\" or \"e\"!");
      }
    } while (true);
  }

  /**
   * オートモードの処理
   */
  private static void auto() {
    // プレイヤー1
    RPSPlayer rpsPlayerOne = new RPSPlayer();
    // プレイヤー2
    RPSPlayer rpsPlayerTwo = new RPSPlayer();

    do {
      // 出し手をセットする
      int pOne = rpsPlayerOne.go().getId();
      int pTwo = rpsPlayerTwo.go().getId();

      switch (pOne) {
        // グー
        case 1:
          if (pTwo == 1) {
            println("プレイヤー1：グー");
            println("プレイヤー2：グー");
            println("あいこ");
          }
          if (pTwo == 2) {
            println("プレイヤー1：グー");
            println("プレイヤー2：チョキ");
            rpsPlayerOne.onResult(true);
            rpsPlayerTwo.onResult(false);
            println("プレイヤー1の勝ち");
          }
          if (pTwo == 3) {
            println("プレイヤー1：グー");
            println("プレイヤー2：パー");
            rpsPlayerOne.onResult(false);
            rpsPlayerTwo.onResult(true);;
            println("プレイヤー2の勝ち");
          }

          break;

        // チョキ
        case 2:
          if (pTwo == 1) {
            println("プレイヤー1：チョキ");
            println("プレイヤー2：グー");
            rpsPlayerOne.onResult(false);
            rpsPlayerTwo.onResult(true);;
            println("プレイヤー2の勝ち");
          }
          if (pTwo == 2) {
            println("プレイヤー1：チョキ");
            println("プレイヤー2：チョキ");
            println("あいこ");
          }
          if (pTwo == 3) {
            println("プレイヤー1：チョキ");
            println("プレイヤー2：パー");
            rpsPlayerOne.onResult(true);
            rpsPlayerTwo.onResult(false);
            println("プレイヤー1の勝ち");
          }
          break;

        // パー
        case 3:
          if (pTwo == 1) {
            println("プレイヤー1：パー");
            println("プレイヤー2：グー");
            rpsPlayerOne.onResult(true);
            rpsPlayerTwo.onResult(false);
            println("プレイヤー1の勝ち");
          }
          if (pTwo == 2) {
            println("プレイヤー1：パー");
            println("プレイヤー2：チョキ");
            rpsPlayerOne.onResult(false);
            rpsPlayerTwo.onResult(true);
            println("プレイヤー1の勝ち");
          }
          if (pTwo == 3) {
            println("プレイヤー1：パー");
            println("プレイヤー2：パー");
            println("あいこ");
          }
          break;
        default:
          break;
      }
      // 勝敗数合計が50であれば、じゃんけんを終了する
    } while (rpsPlayerOne.getWinCount() + rpsPlayerOne.getLoseCount() != 50);

    // 対戦成績表示
    println("-----------------------------------------");
    println("対戦成績");
    println("プレイヤー1：" + rpsPlayerOne.getWinCount() + "勝");
    println("プレイヤー2：" + rpsPlayerTwo.getWinCount() + "勝");
    println("-----------------------------------------");
  }


  private static void println(String msg) {
    System.out.println(msg);
  }

  private static void printCommand() {
    System.out.println("Please enter the following command");
    System.out.println("**********************************");
    System.out.println("*1:グー                          *");
    System.out.println("*2:チョキ                        *");
    System.out.println("*3:パー                          *");
    System.out.println("*e:終了                          *");
    System.out.println("**********************************");
  }

  private static void printMenu() {
    System.out.println("Please enter the following command");
    System.out.println("**********************************");
    System.out.println("*m:マニュアルじゃんけん          *");
    System.out.println("*a:オートじゃんけん              *");
    System.out.println("*e:終了                          *");
    System.out.println("**********************************");
  }
}
