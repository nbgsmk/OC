package cc.kostic.oc.model;

public enum TIP {
	PUT("put"),
	CALL("call"),
	;

	private String opis;

	TIP(String opis) {
		this.opis = opis;
	}


}
