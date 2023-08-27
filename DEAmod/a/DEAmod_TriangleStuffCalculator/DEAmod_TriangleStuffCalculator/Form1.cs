using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
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

            tb_result.Text = "{\r\n      \"id\": \"CustomTriangleWeapon__" + Guid.NewGuid().ToString() + "\",\r\n      \"size\": \"MEDIUM\",\r\n      \"type\": \"DECORATIVE\",\r\n      \"mount\": \"HIDDEN\",\r\n      \"arc\": " + ABDistance + ",\r\n      \"angle\": " + angle + ",\r\n      \"locations\": [" + pointB.x + ", " + pointB.y + "],\r\n      \"renderOrderMod\": " + BCDistance + "\r\n    },";


        }
    }
}
