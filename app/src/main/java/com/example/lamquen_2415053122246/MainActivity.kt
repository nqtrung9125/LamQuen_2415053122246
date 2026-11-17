package com.example.lamquen_2415053122246
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lamquen_2415053122246.LuotNhan
import com.example.lamquen_2415053122246.QuyTacPhieu_2246
class MainActivity : AppCompatActivity() {

    private lateinit var edtTen: EditText
    private lateinit var edtSoLuong: EditText
    private lateinit var txtKetQua: TextView
    private lateinit var txtMaLuot: TextView

    // Model lượt hợp lệ cuối; null khi lỗi hoặc Xóa
    var luotCuoi_2246: LuotNhan? = null

    // Bộ đếm mã: chỉ tăng khi hợp lệ, không reset khi Xóa
    private var boDem_2246 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.txtSinhVien).text =
            "NGUYỄN QUỐC TRUNG — 2415053122246 — TRY20"
        edtTen = findViewById(R.id.edtTen)
        edtSoLuong = findViewById(R.id.edtSoLuong)
        txtKetQua = findViewById(R.id.txtKetQua)
        txtMaLuot = findViewById(R.id.txtMaLuot)
        findViewById<Button>(R.id.btnKiemTra).setOnClickListener { kiemTra_2246() }
        findViewById<Button>(R.id.btnXoa).setOnClickListener { xoa_2246() }
    }

    fun kiemTra_2246() {
        // Xóa trạng thái cũ trước mỗi lượt
        edtTen.error = null
        edtSoLuong.error = null
        luotCuoi_2246 = null
        hienKetQua_2246()

        val ten = edtTen.text.toString().chuanHoaTen_2246()
        if (ten.length !in 2..40) {
            edtTen.error = "Tên phải dài 2–40 ký tự"
            return
        }
        val chuoiSo = edtSoLuong.text.toString().trim()
        val soLuong = if (chuoiSo.isNotEmpty() && chuoiSo.all { it in '0'..'9' })
            chuoiSo.toIntOrNull() else null
        if (soLuong == null || soLuong !in 1..9) {
            edtSoLuong.error = "Số lượng phải là số nguyên 1–9"
            return
        }
        boDem_2246++
        val ma = QuyTacPhieu_2246.taoMaPhieu_2246(boDem_2246)
        luotCuoi_2246 = LuotNhan(ma, ten, soLuong)
        hienKetQua_2246()
    }
    fun xoa_2246() {
        edtTen.setText("")
        edtSoLuong.setText("")
        edtTen.error = null
        edtSoLuong.error = null
        luotCuoi_2246 = null
        hienKetQua_2246()
    }

    // Luôn đọc từ model đã lưu
    private fun hienKetQua_2246() {
        val luot = luotCuoi_2246
        if (luot == null) {
            txtKetQua.text = ""
            txtMaLuot.text = ""
        } else {
            txtKetQua.text = "Xin chào ${luot.hoTen_2246}, số lượng: ${luot.soLuong_2246}"
            txtMaLuot.text = luot.maLuot_2246
        }
    }
}
