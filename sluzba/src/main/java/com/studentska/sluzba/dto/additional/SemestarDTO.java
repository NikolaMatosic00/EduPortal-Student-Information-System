package com.studentska.sluzba.dto.additional;


public class SemestarDTO {
	    private int id;
	    private int broj;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getBroj() {
			return broj;
		}
		public void setBroj(int broj) {
			this.broj = broj;
		}
		public SemestarDTO(int id, int broj) {
			super();
			this.id = id;
			this.broj = broj;
		}
}
