package solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by ohdonggeon on 2020/03/17 8:20 PM
 * Dankook UNIV. Computer Science
 * Source : https://www.acmicpc.net/problem/2798
 * Desc : 카드의 합이 21을 넘지 않는 한도 내에서, 카드의 합을 최대한 크게 만드는 값을 출력하시오
 * Input : 카드의 갯수, 한도 값, 카드에 쓰여져 있는 수
 * Output : 카드를 선택해서 한도 내 가장 큰 숫자
 */
public class Blackjack {

	private static final int PICK_NUMBER = 3;

	private final int cardSize;
	private final int limit;
	private final int[] cards;

	private static int answer;

	Blackjack(int cardSize, int limit, int[] cards) {
		this.cardSize = cardSize;
		this.limit = limit;
		this.cards = cards;
	}

	public int getAnswer() {
		boolean[] visited = new boolean[cardSize];
		getAnswer(0, 0, 0, visited);
		return answer;
	}

	private void getAnswer(int value, int index, int pick, boolean[] visited) {

		if(value > limit || index == cardSize) {
			return;
		}

		if(visited[index]) {
			return;
		}

		if(pick == PICK_NUMBER) {
			if(answer < value) {
				answer = value;
			}
			return;
		}

		for(int i = 0; i < cardSize; i++) {
			visited[index] = true;
			value += cards[index];
			getAnswer(value + cards[index], index + 1, pick + 1, visited);

			visited[index] = false;
			value -= cards[index];
			getAnswer(value, index + 1, pick, visited);
		}
	}

	private void getAnswer(int value, int index, int pick) {
		if (index == cardSize || value > limit) {
			return;
		}

		if (pick == PICK_NUMBER) {
			answer = Math.max(answer, value);
			return;
		}

		for (int i = 0; i < cardSize; i++) {
			getAnswer(value + cards[i], 0, pick + 1);
		}

		/*getAnswer(value + cards[index], index + 1, pick + 1);
		getAnswer(value, index + 1, pick);*/
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

		int cardSize = Integer.parseInt(tokenizer.nextToken());
		int limit = Integer.parseInt(tokenizer.nextToken());
		int[] cards = new int[cardSize];

		StringTokenizer cardTokenizer = new StringTokenizer(reader.readLine());

		for (int index = 0; index < cardSize; index++) {
			cards[index] = Integer.parseInt(cardTokenizer.nextToken());
		}

		Blackjack blackjack = new Blackjack(cardSize, limit, cards);
		System.out.println(blackjack.getAnswer());
	}
}