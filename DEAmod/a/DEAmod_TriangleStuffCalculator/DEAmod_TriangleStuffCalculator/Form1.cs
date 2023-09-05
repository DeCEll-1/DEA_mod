using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DEAmod_TriangleStuffCalculator
{

    public partial class Form1 : Form
    {
        public Form1()
        {



            InitializeComponent();
        }


        public bool IsNegative(float number) { return number < 0; }

        public bool IsPositive(float number) { return number < 0; }

        public bool IsZero(float number) { return number == 0; }


        const double Rad2Deg = 180.0 / Math.PI;
        const double Deg2Rad = Math.PI / 180.0;

        private double Angle(point start, point end)
        {
            return Math.Atan2(start.y - end.y, end.x - start.x) * Rad2Deg;
        }



        public double GetDistance(point vector1, point vector2)
        {
            return Math.Sqrt(Math.Pow((vector2.x - vector1.x), 2) + Math.Pow((vector2.y - vector1.y), 2));
        }

        private Color color;

        private void btn_enter_Click(object sender, EventArgs e)
        {
            point pointA = new point((float)Convert.ToDouble(tb_PointAX.Text), (float)Convert.ToDouble(tb_PointAY.Text));
            point pointB = new point((float)Convert.ToDouble(tb_PointBX.Text), (float)Convert.ToDouble(tb_PointBY.Text));
            point pointC = new point((float)Convert.ToDouble(tb_PointCX.Text), (float)Convert.ToDouble(tb_PointCY.Text));



            double angle = Angle(pointB, pointA);//angle of the weapon

            if (angle == -90)
            {
                angle = 90;
            }

            if (angle == 90)
            {
                angle = -90;
            }

            double ABDistance = GetDistance(pointB, pointA);//"arc"

            double BCDistance = GetDistance(pointC, pointB);//"order"

            //pointB is the weapons location


            pnl_Color.BackColor = Color.FromArgb(Convert.ToInt32(tb_A.Text),//updates the panel
                                                         Convert.ToInt32(tb_R.Text),
                                                         Convert.ToInt32(tb_G.Text),
                                                         Convert.ToInt32(tb_B.Text));

            color = Color.White;//just to be safe

            try
            {
                int r = pnl_Color.BackColor.R;
                int g = pnl_Color.BackColor.G;
                int b = pnl_Color.BackColor.B;
                int a = pnl_Color.BackColor.A;

                color = Color.FromArgb(a, r, g, b);//gets the color from the panel
            }
            catch { }

            String DrawType="";

            if (cb_Filled.Checked)
            {
                DrawType = "HARDPOINT";
            }

            if (cb_Lines.Checked)
            {
                DrawType = "TURRET";
            }

            if (cb_DoNotDraw.Checked)
            {
                DrawType = "HIDDEN";
            }




            //tb_result.Text = $"{{\r\n      \"id\": \"CustomTriangleWeapon__{Guid.NewGuid().ToString().ToLower()}__R{color.R}__G{color.G}__B{color.B}__A{color.A}\",\r\n      \"size\": \"MEDIUM\",\r\n      \"type\": \"DECORATIVE\",\r\n      \"mount\": \"HIDDEN\",\r\n      \"arc\": {ABDistance},\r\n      \"angle\": {angle},\r\n      \"locations\": [{pointB.x}, {pointB.y}],\r\n      \"renderOrderMod\": {BCDistance}\r\n    }},";

            if (color.A.ToString("X").Length == 2)
            {
                //if hex of alpha is less than 2 characters lenght
                tb_result.Text = $"{{\r\n      \"id\": \"CustomTriangleWeapon__{Guid.NewGuid().ToString().ToLower()}__{ColorTranslator.ToHtml(color) + color.A.ToString("X")}\",\r\n      \"size\": \"MEDIUM\",\r\n      \"type\": \"DECORATIVE\",\r\n      \"mount\": \"{DrawType}\",\r\n      \"arc\": {ABDistance},\r\n      \"angle\": {angle},\r\n      \"locations\": [{pointB.x}, {pointB.y}],\r\n      \"renderOrderMod\": {BCDistance}\r\n    }},";
            }
            else
            {
                //if hex of alpha is more than 1 character lenght
                tb_result.Text = $"{{\r\n      \"id\": \"CustomTriangleWeapon__{Guid.NewGuid().ToString().ToLower()}__{ColorTranslator.ToHtml(color) + "0" + color.A.ToString("X")}\",\r\n      \"size\": \"MEDIUM\",\r\n      \"type\": \"DECORATIVE\",\r\n      \"mount\": \"{DrawType}\",\r\n      \"arc\": {ABDistance},\r\n      \"angle\": {angle},\r\n      \"locations\": [{pointB.x}, {pointB.y}],\r\n      \"renderOrderMod\": {BCDistance}\r\n    }},";
            }





        }

        private void Form1_KeyPress(object sender, KeyPressEventArgs e)
        {

            if (ModifierKeys.HasFlag(Keys.Control) && e.KeyChar == '\u0017')
            {
                this.Close();
            }

        }

        private void KeyCheck(object sender, KeyPressEventArgs e)
        {

            if (e.KeyChar == '-')
            {
                e.Handled = false;
                return;
            }

            if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar) &&
        (e.KeyChar != '.'))
            {
                e.Handled = true;
            }

            // only allow one decimal point
            if ((e.KeyChar == '.') && ((sender as TextBox).Text.IndexOf('.') > -1))
            {
                e.Handled = true;
            }
        }

        private void KeyCheckColor(object sender, KeyPressEventArgs e)
        {

            try
            {


                if (Convert.ToInt32((sender as TextBox).Text + e.KeyChar.ToString()) > 255)
                {
                    e.Handled = true;

                    if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar) &&
                (e.KeyChar == '.'))
                    {
                        e.Handled = true;

                    }
                    pnl_Color.BackColor = Color.FromArgb(Convert.ToInt32(tb_A.Text),//updates the panel
                                                        Convert.ToInt32(tb_R.Text),
                                                        Convert.ToInt32(tb_G.Text),
                                                        Convert.ToInt32(tb_B.Text));
                }



            }
            catch { }
        }

        private void btn_enter_Enter(object sender, EventArgs e)
        {
            try
            {
                pnl_Color.BackColor = Color.FromArgb(Convert.ToInt32(tb_A.Text),//updates the panel
                                                        Convert.ToInt32(tb_R.Text),
                                                        Convert.ToInt32(tb_G.Text),
                                                        Convert.ToInt32(tb_B.Text));
            }
            catch { }
        }

        private void KeyUp(object sender, KeyEventArgs e)
        {
            try
            {
                pnl_Color.BackColor = Color.FromArgb(Convert.ToInt32(tb_A.Text),//updates the panel
                                                        Convert.ToInt32(tb_R.Text),
                                                        Convert.ToInt32(tb_G.Text),
                                                        Convert.ToInt32(tb_B.Text));
            }
            catch { }
        }
    }
}
