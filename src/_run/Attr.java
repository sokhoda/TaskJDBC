package _run;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Attr {
	private String atName;
	private String atValue;

	public Attr(String atName, String atValue) {
		this.atName = atName;
		this.atValue = atValue;

	}

	public static void main(String[] args) {
		HashMap<String, List<Attr>> map = new HashMap<>();
		List<Attr> list1 = Arrays.asList(new Attr("createBtnWidth", "100"),
				new Attr("countryLbl", " рањна:"));
		map.put("command1", list1);

		List<Attr> list2 = Arrays.asList(new Attr("createBtnVisible", "false"),
				new Attr("regionLbl", "ќблать/територ. одиниц€:"));
		map.put("command2", list2);

		for (Entry<String, List<Attr>> elem : map.entrySet()) {
			System.out.println(elem.getKey() + ":" + elem.getValue());
		}
		// System.out.println(map);

	}

	@Override
	public String toString() {
		return "\nCommandProc [atName=" + atName + ", atValue=" + atValue + "]";
	}

}
