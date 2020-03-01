package editdistancedyn;
public class Quadrupla <F, S, T, R> {
	private F first;
	private S second;
	private T third;
	private R fourth;

	public Quadrupla (F first, S second, T third, R fourth) {
		this.first=first;
		this.second=second;
		this.third=third;
		this.fourth=fourth;
	}

	public F getFirst () {
		return first;
	}

	public S getSecond () {
		return second;
	}

	public T getThird () {
		return third;
	}

	public R getFourth () {
		return fourth;
	}

	@Override
    public int hashCode() {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode()) ^ (third == null ? 0 : third.hashCode()) ^ (fourth == null ? 0 : fourth.hashCode());
    }
}