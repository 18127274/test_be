package DemoWebAPI.model;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "NGHIPHEP")
public class NghiPhep {
	@Id
	private long ID;
	private long MaNguoiDuyet;
	private long LoaiNghiPhep;
	private String NgayBatDau;
	private String NgayKetThuc;
	private String LyDo;
	private String LyDoTuChoi;
	private int PhepHopLe;
	private int TrangThai;
	
	
	public long getID() {
		return ID;
	}


	public void setID(long iD) {
		ID = iD;
	}



	public long getMaNguoiDuyet() {
		return MaNguoiDuyet;
	}


	public void setMaNguoiDuyet(long maNguoiDuyet) {
		MaNguoiDuyet = maNguoiDuyet;
	}


	public long getLoaiNghiPhep() {
		return LoaiNghiPhep;
	}


	public void setLoaiNghiPhep(long loaiNghiPhep) {
		LoaiNghiPhep = loaiNghiPhep;
	}


	public String getNgayBatDau() {
		return NgayBatDau;
	}


	public void setNgayBatDau(String ngayBatDau) {
		NgayBatDau = ngayBatDau;
	}


	public String getNgayKetThuc() {
		return NgayKetThuc;
	}


	public void setNgayKetThuc(String ngayKetThuc) {
		NgayKetThuc = ngayKetThuc;
	}


	public String getLyDo() {
		return LyDo;
	}


	public void setLyDo(String lyDo) {
		LyDo = lyDo;
	}


	public String getLyDoTuChoi() {
		return LyDoTuChoi;
	}


	public void setLyDoTuChoi(String lyDoTuChoi) {
		LyDoTuChoi = lyDoTuChoi;
	}


	public int getPhepHopLe() {
		return PhepHopLe;
	}


	public void setPhepHopLe(int phepHopLe) {
		PhepHopLe = phepHopLe;
	}


	public int getTrangThai() {
		return TrangThai;
	}


	public void setTrangThai(int trangThai) {
		TrangThai = trangThai;
	}

	
	public NghiPhep(long iD, long maNguoiDuyet, long loaiNghiPhep, String ngayBatDau, String ngayKetThuc, String lyDo,
			String lyDoTuChoi, int phepHopLe, int trangThai) {
		super();
		ID = iD;
		MaNguoiDuyet = maNguoiDuyet;
		LoaiNghiPhep = loaiNghiPhep;
		NgayBatDau = ngayBatDau;
		NgayKetThuc = ngayKetThuc;
		LyDo = lyDo;
		LyDoTuChoi = lyDoTuChoi;
		PhepHopLe = phepHopLe;
		TrangThai = trangThai;
	}


	public NghiPhep() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
