namespace DEAmod_TriangleStuffCalculator
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.tb_result = new System.Windows.Forms.TextBox();
            this.tb_PointAX = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.tb_PointBX = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.tb_PointCX = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.tb_PointCY = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.tb_PointBY = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.tb_PointAY = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.btn_enter = new System.Windows.Forms.Button();
            this.pnl_Color = new System.Windows.Forms.Panel();
            this.tb_R = new System.Windows.Forms.TextBox();
            this.tb_G = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.tb_B = new System.Windows.Forms.TextBox();
            this.tb_A = new System.Windows.Forms.TextBox();
            this.label9 = new System.Windows.Forms.Label();
            this.label10 = new System.Windows.Forms.Label();
            this.label11 = new System.Windows.Forms.Label();
            this.cb_Filled = new System.Windows.Forms.RadioButton();
            this.cb_Lines = new System.Windows.Forms.RadioButton();
            this.cb_DoNotDraw = new System.Windows.Forms.RadioButton();
            this.pnl_Texture = new System.Windows.Forms.Panel();
            this.tb_TextureLocationPath = new System.Windows.Forms.TextBox();
            this.btn_TextureRefresh = new System.Windows.Forms.Button();
            this.cb_AddColor = new System.Windows.Forms.CheckBox();
            this.tb_ModRoot = new System.Windows.Forms.TextBox();
            this.label12 = new System.Windows.Forms.Label();
            this.label13 = new System.Windows.Forms.Label();
            this.cb_AddImage = new System.Windows.Forms.CheckBox();
            this.lbl_SizeLBL = new System.Windows.Forms.Label();
            this.tb_TextureID = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // tb_result
            // 
            this.tb_result.Location = new System.Drawing.Point(12, 268);
            this.tb_result.Multiline = true;
            this.tb_result.Name = "tb_result";
            this.tb_result.Size = new System.Drawing.Size(311, 210);
            this.tb_result.TabIndex = 0;
            // 
            // tb_PointAX
            // 
            this.tb_PointAX.Location = new System.Drawing.Point(12, 25);
            this.tb_PointAX.Name = "tb_PointAX";
            this.tb_PointAX.Size = new System.Drawing.Size(100, 20);
            this.tb_PointAX.TabIndex = 1;
            this.tb_PointAX.Text = "0";
            this.tb_PointAX.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.KeyCheck);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(51, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Point A X";
            // 
            // tb_PointBX
            // 
            this.tb_PointBX.Location = new System.Drawing.Point(12, 64);
            this.tb_PointBX.Name = "tb_PointBX";
            this.tb_PointBX.Size = new System.Drawing.Size(100, 20);
            this.tb_PointBX.TabIndex = 3;
            this.tb_PointBX.Text = "0";
            this.tb_PointBX.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.KeyCheck);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 48);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(51, 13);
            this.label2.TabIndex = 2;
            this.label2.Text = "Point B X";
            // 
            // tb_PointCX
            // 
            this.tb_PointCX.Location = new System.Drawing.Point(12, 103);
            this.tb_PointCX.Name = "tb_PointCX";
            this.tb_PointCX.Size = new System.Drawing.Size(100, 20);
            this.tb_PointCX.TabIndex = 5;
            this.tb_PointCX.Text = "0";
            this.tb_PointCX.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.KeyCheck);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(12, 87);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(51, 13);
            this.label3.TabIndex = 2;
            this.label3.Text = "Point C X";
            // 
            // tb_PointCY
            // 
            this.tb_PointCY.Location = new System.Drawing.Point(118, 103);
            this.tb_PointCY.Name = "tb_PointCY";
            this.tb_PointCY.Size = new System.Drawing.Size(100, 20);
            this.tb_PointCY.TabIndex = 6;
            this.tb_PointCY.Text = "0";
            this.tb_PointCY.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.KeyCheck);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(118, 87);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(51, 13);
            this.label4.TabIndex = 2;
            this.label4.Text = "Point C Y";
            // 
            // tb_PointBY
            // 
            this.tb_PointBY.Location = new System.Drawing.Point(121, 64);
            this.tb_PointBY.Name = "tb_PointBY";
            this.tb_PointBY.Size = new System.Drawing.Size(100, 20);
            this.tb_PointBY.TabIndex = 4;
            this.tb_PointBY.Text = "0";
            this.tb_PointBY.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.KeyCheck);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(121, 48);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(51, 13);
            this.label6.TabIndex = 2;
            this.label6.Text = "Point B Y";
            // 
            // tb_PointAY
            // 
            this.tb_PointAY.Location = new System.Drawing.Point(121, 25);
            this.tb_PointAY.Name = "tb_PointAY";
            this.tb_PointAY.Size = new System.Drawing.Size(100, 20);
            this.tb_PointAY.TabIndex = 2;
            this.tb_PointAY.Text = "0";
            this.tb_PointAY.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.KeyCheck);
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(121, 9);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(51, 13);
            this.label7.TabIndex = 2;
            this.label7.Text = "Point A Y";
            // 
            // btn_enter
            // 
            this.btn_enter.Location = new System.Drawing.Point(15, 239);
            this.btn_enter.Name = "btn_enter";
            this.btn_enter.Size = new System.Drawing.Size(310, 23);
            this.btn_enter.TabIndex = 99;
            this.btn_enter.Text = "heheheheheheeheheh";
            this.btn_enter.UseVisualStyleBackColor = true;
            this.btn_enter.Click += new System.EventHandler(this.btn_enter_Click);
            this.btn_enter.Enter += new System.EventHandler(this.btn_enter_Enter);
            this.btn_enter.Leave += new System.EventHandler(this.btn_enter_Enter);
            // 
            // pnl_Color
            // 
            this.pnl_Color.Location = new System.Drawing.Point(224, 126);
            this.pnl_Color.Name = "pnl_Color";
            this.pnl_Color.Size = new System.Drawing.Size(101, 75);
            this.pnl_Color.TabIndex = 8;
            // 
            // tb_R
            // 
            this.tb_R.Enabled = false;
            this.tb_R.Location = new System.Drawing.Point(12, 142);
            this.tb_R.MaxLength = 3;
            this.tb_R.Name = "tb_R";
            this.tb_R.Size = new System.Drawing.Size(100, 20);
            this.tb_R.TabIndex = 7;
            this.tb_R.Text = "255";
            this.tb_R.Enter += new System.EventHandler(this.btn_enter_Enter);
            this.tb_R.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.KeyCheckColor);
            this.tb_R.KeyUp += new System.Windows.Forms.KeyEventHandler(this.KeyUp);
            this.tb_R.Leave += new System.EventHandler(this.btn_enter_Enter);
            // 
            // tb_G
            // 
            this.tb_G.Enabled = false;
            this.tb_G.Location = new System.Drawing.Point(118, 142);
            this.tb_G.MaxLength = 3;
            this.tb_G.Name = "tb_G";
            this.tb_G.Size = new System.Drawing.Size(100, 20);
            this.tb_G.TabIndex = 8;
            this.tb_G.Text = "255";
            this.tb_G.Enter += new System.EventHandler(this.btn_enter_Enter);
            this.tb_G.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.KeyCheckColor);
            this.tb_G.Leave += new System.EventHandler(this.btn_enter_Enter);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(12, 126);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(15, 13);
            this.label5.TabIndex = 2;
            this.label5.Text = "R";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(118, 126);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(15, 13);
            this.label8.TabIndex = 2;
            this.label8.Text = "G";
            // 
            // tb_B
            // 
            this.tb_B.Enabled = false;
            this.tb_B.Location = new System.Drawing.Point(12, 181);
            this.tb_B.MaxLength = 3;
            this.tb_B.Name = "tb_B";
            this.tb_B.Size = new System.Drawing.Size(100, 20);
            this.tb_B.TabIndex = 9;
            this.tb_B.Text = "255";
            this.tb_B.Enter += new System.EventHandler(this.btn_enter_Enter);
            this.tb_B.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.KeyCheckColor);
            this.tb_B.Leave += new System.EventHandler(this.btn_enter_Enter);
            // 
            // tb_A
            // 
            this.tb_A.Enabled = false;
            this.tb_A.Location = new System.Drawing.Point(118, 181);
            this.tb_A.MaxLength = 3;
            this.tb_A.Name = "tb_A";
            this.tb_A.Size = new System.Drawing.Size(100, 20);
            this.tb_A.TabIndex = 10;
            this.tb_A.Text = "255";
            this.tb_A.Enter += new System.EventHandler(this.btn_enter_Enter);
            this.tb_A.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.KeyCheckColor);
            this.tb_A.Leave += new System.EventHandler(this.btn_enter_Enter);
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(12, 165);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(14, 13);
            this.label9.TabIndex = 2;
            this.label9.Text = "B";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(118, 165);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(14, 13);
            this.label10.TabIndex = 2;
            this.label10.Text = "A";
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(12, 208);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(59, 13);
            this.label11.TabIndex = 101;
            this.label11.Text = "Draw Type";
            // 
            // cb_Filled
            // 
            this.cb_Filled.AutoSize = true;
            this.cb_Filled.Checked = true;
            this.cb_Filled.Location = new System.Drawing.Point(77, 208);
            this.cb_Filled.Name = "cb_Filled";
            this.cb_Filled.Size = new System.Drawing.Size(49, 17);
            this.cb_Filled.TabIndex = 11;
            this.cb_Filled.TabStop = true;
            this.cb_Filled.Text = "Filled";
            this.cb_Filled.UseVisualStyleBackColor = true;
            // 
            // cb_Lines
            // 
            this.cb_Lines.AutoSize = true;
            this.cb_Lines.Location = new System.Drawing.Point(124, 208);
            this.cb_Lines.Name = "cb_Lines";
            this.cb_Lines.Size = new System.Drawing.Size(74, 17);
            this.cb_Lines.TabIndex = 12;
            this.cb_Lines.Text = "Only Lines";
            this.cb_Lines.UseVisualStyleBackColor = true;
            // 
            // cb_DoNotDraw
            // 
            this.cb_DoNotDraw.AutoSize = true;
            this.cb_DoNotDraw.Location = new System.Drawing.Point(204, 208);
            this.cb_DoNotDraw.Name = "cb_DoNotDraw";
            this.cb_DoNotDraw.Size = new System.Drawing.Size(87, 17);
            this.cb_DoNotDraw.TabIndex = 13;
            this.cb_DoNotDraw.Text = "Do Not Draw";
            this.cb_DoNotDraw.UseVisualStyleBackColor = true;
            // 
            // pnl_Texture
            // 
            this.pnl_Texture.BackgroundImage = global::DEAmod_TriangleStuffCalculator.Properties.Resources.NoTextureSelected;
            this.pnl_Texture.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.pnl_Texture.Location = new System.Drawing.Point(329, 268);
            this.pnl_Texture.Name = "pnl_Texture";
            this.pnl_Texture.Size = new System.Drawing.Size(210, 210);
            this.pnl_Texture.TabIndex = 102;
            // 
            // tb_TextureLocationPath
            // 
            this.tb_TextureLocationPath.Location = new System.Drawing.Point(297, 208);
            this.tb_TextureLocationPath.Name = "tb_TextureLocationPath";
            this.tb_TextureLocationPath.Size = new System.Drawing.Size(242, 20);
            this.tb_TextureLocationPath.TabIndex = 14;
            this.tb_TextureLocationPath.Text = "Path Relative To Mod Folder, isnt used in weapon";
            this.tb_TextureLocationPath.Enter += new System.EventHandler(this.deletePlaceHolder);
            // 
            // btn_TextureRefresh
            // 
            this.btn_TextureRefresh.Location = new System.Drawing.Point(404, 239);
            this.btn_TextureRefresh.Name = "btn_TextureRefresh";
            this.btn_TextureRefresh.Size = new System.Drawing.Size(135, 23);
            this.btn_TextureRefresh.TabIndex = 16;
            this.btn_TextureRefresh.Text = "Refresh Texture";
            this.btn_TextureRefresh.UseVisualStyleBackColor = true;
            this.btn_TextureRefresh.Click += new System.EventHandler(this.btn_TextureRefresh_Click);
            // 
            // cb_AddColor
            // 
            this.cb_AddColor.AutoSize = true;
            this.cb_AddColor.Location = new System.Drawing.Point(224, 105);
            this.cb_AddColor.Name = "cb_AddColor";
            this.cb_AddColor.Size = new System.Drawing.Size(72, 17);
            this.cb_AddColor.TabIndex = 7;
            this.cb_AddColor.Text = "Add Color";
            this.cb_AddColor.UseVisualStyleBackColor = true;
            this.cb_AddColor.CheckedChanged += new System.EventHandler(this.cb_AddColor_CheckedChanged);
            // 
            // tb_ModRoot
            // 
            this.tb_ModRoot.Location = new System.Drawing.Point(227, 25);
            this.tb_ModRoot.Multiline = true;
            this.tb_ModRoot.Name = "tb_ModRoot";
            this.tb_ModRoot.Size = new System.Drawing.Size(316, 36);
            this.tb_ModRoot.TabIndex = 0;
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(224, 9);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(91, 13);
            this.label12.TabIndex = 106;
            this.label12.Text = "Mods Root Folder";
            // 
            // label13
            // 
            this.label13.AutoSize = true;
            this.label13.Location = new System.Drawing.Point(331, 185);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(50, 13);
            this.label13.TabIndex = 107;
            this.label13.Text = "Image ID";
            // 
            // cb_AddImage
            // 
            this.cb_AddImage.AutoSize = true;
            this.cb_AddImage.Checked = true;
            this.cb_AddImage.CheckState = System.Windows.Forms.CheckState.Checked;
            this.cb_AddImage.Location = new System.Drawing.Point(329, 243);
            this.cb_AddImage.Name = "cb_AddImage";
            this.cb_AddImage.Size = new System.Drawing.Size(77, 17);
            this.cb_AddImage.TabIndex = 15;
            this.cb_AddImage.Text = "Add Image";
            this.cb_AddImage.UseVisualStyleBackColor = true;
            this.cb_AddImage.CheckedChanged += new System.EventHandler(this.cb_AddImage_CheckedChanged);
            // 
            // lbl_SizeLBL
            // 
            this.lbl_SizeLBL.AutoSize = true;
            this.lbl_SizeLBL.Location = new System.Drawing.Point(539, 481);
            this.lbl_SizeLBL.Name = "lbl_SizeLBL";
            this.lbl_SizeLBL.Size = new System.Drawing.Size(0, 13);
            this.lbl_SizeLBL.TabIndex = 108;
            // 
            // tb_TextureID
            // 
            this.tb_TextureID.Location = new System.Drawing.Point(387, 182);
            this.tb_TextureID.Name = "tb_TextureID";
            this.tb_TextureID.Size = new System.Drawing.Size(152, 20);
            this.tb_TextureID.TabIndex = 14;
            this.tb_TextureID.Text = "ID of the image in settings.json";
            this.tb_TextureID.Enter += new System.EventHandler(this.deletePlaceHolder);
            // 
            // Form1
            // 
            this.AcceptButton = this.btn_enter;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoSize = true;
            this.AutoSizeMode = System.Windows.Forms.AutoSizeMode.GrowAndShrink;
            this.ClientSize = new System.Drawing.Size(555, 490);
            this.Controls.Add(this.lbl_SizeLBL);
            this.Controls.Add(this.cb_AddImage);
            this.Controls.Add(this.label13);
            this.Controls.Add(this.label12);
            this.Controls.Add(this.cb_AddColor);
            this.Controls.Add(this.btn_TextureRefresh);
            this.Controls.Add(this.tb_ModRoot);
            this.Controls.Add(this.tb_TextureID);
            this.Controls.Add(this.tb_TextureLocationPath);
            this.Controls.Add(this.pnl_Texture);
            this.Controls.Add(this.cb_DoNotDraw);
            this.Controls.Add(this.cb_Lines);
            this.Controls.Add(this.cb_Filled);
            this.Controls.Add(this.label11);
            this.Controls.Add(this.pnl_Color);
            this.Controls.Add(this.btn_enter);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label10);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.tb_A);
            this.Controls.Add(this.tb_PointAY);
            this.Controls.Add(this.tb_G);
            this.Controls.Add(this.tb_PointBY);
            this.Controls.Add(this.tb_B);
            this.Controls.Add(this.tb_PointCY);
            this.Controls.Add(this.tb_R);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.tb_PointCX);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.tb_PointBX);
            this.Controls.Add(this.tb_PointAX);
            this.Controls.Add(this.tb_result);
            this.KeyPreview = true;
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "make triangles";
            this.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.Form1_KeyPress);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox tb_result;
        private System.Windows.Forms.TextBox tb_PointAX;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox tb_PointBX;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox tb_PointCX;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox tb_PointCY;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox tb_PointBY;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox tb_PointAY;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Button btn_enter;
        private System.Windows.Forms.Panel pnl_Color;
        private System.Windows.Forms.TextBox tb_R;
        private System.Windows.Forms.TextBox tb_G;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.TextBox tb_B;
        private System.Windows.Forms.TextBox tb_A;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.RadioButton cb_Filled;
        private System.Windows.Forms.RadioButton cb_Lines;
        private System.Windows.Forms.RadioButton cb_DoNotDraw;
        private System.Windows.Forms.Panel pnl_Texture;
        private System.Windows.Forms.TextBox tb_TextureLocationPath;
        private System.Windows.Forms.Button btn_TextureRefresh;
        private System.Windows.Forms.CheckBox cb_AddColor;
        private System.Windows.Forms.TextBox tb_ModRoot;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.CheckBox cb_AddImage;
        private System.Windows.Forms.Label lbl_SizeLBL;
        private System.Windows.Forms.TextBox tb_TextureID;
    }
}

