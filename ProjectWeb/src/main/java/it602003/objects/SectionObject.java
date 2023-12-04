package it602003.objects;

public class SectionObject {
	private short section_id;
	private String section_name;
	private String section_notes;
	private String section_created_date;
	private int section_manager_id;
	private boolean section_enable;
	private boolean section_delete;
	private String section_last_modified;
	private int section_created_author_id;
	private String section_name_en;
	private byte section_language;

	public SectionObject() {

	}

	public short getSection_id() {
		return section_id;
	}

	public void setSection_id(short section_id) {
		this.section_id = section_id;
	}

	public String getSection_name() {
		return section_name;
	}

	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}

	public String getSection_notes() {
		return section_notes;
	}

	public void setSection_notes(String section_notes) {
		this.section_notes = section_notes;
	}

	public String getSection_created_date() {
		return section_created_date;
	}

	public void setSection_created_date(String section_created_date) {
		this.section_created_date = section_created_date;
	}

	public int getSection_manager_id() {
		return section_manager_id;
	}

	public void setSection_manager_id(int section_manager_id) {
		this.section_manager_id = section_manager_id;
	}

	public boolean isSection_enable() {
		return section_enable;
	}

	public void setSection_enable(boolean section_enable) {
		this.section_enable = section_enable;
	}

	public boolean isSection_delete() {
		return section_delete;
	}

	public void setSection_delete(boolean section_delete) {
		this.section_delete = section_delete;
	}

	public String getSection_last_modified() {
		return section_last_modified;
	}

	public void setSection_last_modified(String section_last_modified) {
		this.section_last_modified = section_last_modified;
	}

	public int getSection_created_author_id() {
		return section_created_author_id;
	}

	public void setSection_created_author_id(int section_created_author_id) {
		this.section_created_author_id = section_created_author_id;
	}

	public String getSection_name_en() {
		return section_name_en;
	}

	public void setSection_name_en(String section_name_en) {
		this.section_name_en = section_name_en;
	}

	public byte getSection_language() {
		return section_language;
	}

	public void setSection_language(byte section_language) {
		this.section_language = section_language;
	}

	@Override
	public String toString() {
		return "ID: " + this.section_id + "\t Name: " + this.section_name;
	}
}
