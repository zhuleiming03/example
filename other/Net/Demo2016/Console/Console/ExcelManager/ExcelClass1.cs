using System;
using System.Text;
using System.Globalization;
using Microsoft.Office.Interop.Excel;
using System.Threading;
using System.Collections;
using System.Diagnostics;
using System.IO;
using System.Reflection;
using System.Runtime.InteropServices;

namespace ConsoleDemo
{
    /// <summary>
    /// <para>封装对Excel的操作</para>
    /// </summary>
    public class ExcelManager : IDisposable
    {
        ApplicationClass App;

        CultureInfo OriginalCulture;

        private string _OpenFileName;
        /// <summary>
        /// 当前打开的文件名
        /// </summary>
        public string OpenFileName
        {
            get { return _OpenFileName; }
        }

        /// <summary>
        /// 返回一个bool值确定当前的文件状态
        /// </summary>
        public bool AnyFileOpen
        {
            // After opening a file, we assign its name to _OpenedFileName. After closing,
            // we clear _OpenedFileName by assigning String.Empty to it.
            // So a String.Empty value shows no file is open
            get { return !String.IsNullOrEmpty(_OpenFileName); }
        }

        private static ExcelManager instance = null;
        private static readonly object excellock = new object();

        public static ExcelManager Instance
        {
            get
            {
                lock (excellock)
                {
                    if (instance == null)
                    {
                        instance = new ExcelManager();
                    }
                    return instance;
                }
            }
        }

        /// <summary>
        /// 默认构造函数.
        /// </summary>
        /// <remarks>
        /// <code>
        /// using(ExcelManager em = new ExcelManager())
        /// {
        ///     // codes using excel file go here, for example:
        ///     em.Open(filename);
        /// }
        /// </code>
        /// </example>
        /// <b>Note:</b> This Constructor changes current thread's culture to "en-US" and returns it to 
        /// previous state after dispose.
        /// </remarks>

        public ExcelManager()
        {
            OriginalCulture = Thread.CurrentThread.CurrentCulture;
            Thread.CurrentThread.CurrentCulture = new CultureInfo("en-US");
            //Thread.CurrentThread.CurrentCulture = new CultureInfo("zh-CN");

            App = new ApplicationClass();
            App.DisplayAlerts = false;
        }

        #region Functions to work with Files (workbooks)

        /// <summary>
        ///新建一个Excel文件.
        /// </summary>
        /// <param name="fileName">生成的文件名</param>
        public void Create(string fileName)
        {
            try
            {

                Close();
                App.Workbooks.Add(XlWBATemplate.xlWBATWorksheet);
                App.ActiveWorkbook.SaveAs(fileName,
                                          XlFileFormat.xlWorkbookNormal,
                                          System.Type.Missing,
                                          System.Type.Missing,
                                          System.Type.Missing,
                                          System.Type.Missing,
                                          XlSaveAsAccessMode.xlNoChange,
                                          System.Type.Missing,
                                          System.Type.Missing,
                                          System.Type.Missing,
                                          System.Type.Missing,
                                          System.Type.Missing);
                _OpenFileName = fileName;
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Error Creating File '{0}'", fileName), err);
            }
        }

        /// <summary>
        /// 新建一个工作表.
        /// </summary>
        /// <param name="NewSheetName"></param>
        public void CreateSheet(string NewSheetName)
        {
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                ((_Worksheet)App.Worksheets.Add(Type.Missing, App.Worksheets[App.Worksheets.Count], Type.Missing, Type.Missing)).Name = NewSheetName;

            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not activate sheet '{0}'", NewSheetName), err);
            }
        }

        /// <summary>
        /// 删除一个工作表.
        /// </summary>
        /// <param name=SheetName"></param>
        public void deleteSheet(string SheetName)
        {
            try
            {
                _Worksheet Sheet = (_Worksheet)App.Worksheets.get_Item(SheetName);
                App.DisplayAlerts = false;
                Sheet.Delete();
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not delete sheet '{0}'", SheetName), err);
            }
        }

        /// <summary>
        ///打开一个Excel文件.
        /// </summary>
        /// <param name="fileName">打开的文件名</param>
        public void Open(string fileName)
        {
            try
            {
                Close();
                Workbook wk = App.Workbooks.Open(fileName,
                                    false,
                                    false,
                                    System.Type.Missing,
                                    System.Type.Missing,
                                    System.Type.Missing,
                                    System.Type.Missing,
                                    System.Type.Missing,
                                    System.Type.Missing,
                                    false,
                                    System.Type.Missing,
                                    System.Type.Missing,
                                    false,
                                    System.Type.Missing,
                                    System.Type.Missing);
                _OpenFileName = fileName;

                if (wk.ReadOnly)
                    throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Readonly"));

            }
            catch (Exception err)
            {
                if (err.Message == "Readonly")
                    throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "'{0}' 文件已经打开，无法操作，请先手工关闭此文件！", fileName), err);
                else
                    throw new Exception(
                        String.Format(CultureInfo.InvariantCulture, "Error Opening File '{0}'", fileName), err);
            }
        }

        /// <summary>
        /// 关闭打开的文件.
        /// </summary>
        public void Close()
        {
            try
            {
                if (App.Workbooks != null) App.Workbooks.Close();
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Error Closing File '{0}'", _OpenFileName), err);
            }
        }


        /// <summary>
        ///保存文件
        /// </summary>
        public void Save()
        {
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            App.ActiveWorkbook.Save();
        }

        /// <summary>
        /// 另存文件
        /// </summary>
        /// <param name="newFileName">新的文件名</param>
        public void SaveAs(string newFileName)
        {
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                if (File.Exists(newFileName))
                {
                    File.Delete(newFileName);
                }
                App.ActiveWorkbook.SaveAs(newFileName,
                                        XlFileFormat.xlWorkbookNormal,
                                        System.Type.Missing,
                                        System.Type.Missing,
                                        System.Type.Missing,
                                        System.Type.Missing,
                                        XlSaveAsAccessMode.xlNoChange,
                                        System.Type.Missing,
                                        System.Type.Missing,
                                        System.Type.Missing,
                                        System.Type.Missing,
                                        System.Type.Missing);

                _OpenFileName = newFileName;
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not save file as '{0}'", newFileName), err);
            }
        }

        #endregion

        #region Functions to work with Worksheets

        /// <summary>
        ///激活指定的工作表.
        /// </summary>
        /// <param name="sheetName">已有的工作表名</param>
        public void ActivateSheet(string sheetName)
        {
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                foreach (_Worksheet wsheet in App.ActiveWorkbook.Sheets)
                    if (String.Compare(wsheet.Name, sheetName, true) == 0)
                    {
                        wsheet.Activate();
                        return;
                    }
                throw new Exception(String.Format("Can not find sheet '{0}'", sheetName));
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not activate sheet '{0}'", sheetName), err);
            }
        }


        public void SetSheetPrntFormat(string SheetName, string PaperSize, double top, double left, double right, double bottom)
        {
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                foreach (_Worksheet wsheet in App.Sheets)
                {
                    if (String.Compare(wsheet.Name, SheetName, true) == 0)
                    {
                        try
                        {
                            switch (PaperSize)
                            {
                                case "A3":
                                    wsheet.PageSetup.PaperSize = XlPaperSize.xlPaperA3;
                                    break;
                                case "A4":
                                    wsheet.PageSetup.PaperSize = XlPaperSize.xlPaperA4;
                                    break;
                            }
                        }
                        catch
                        {
                            throw new Exception(String.Format("打印机可能不支持 '{0}' 尺寸的纸张。", PaperSize));
                        }
                        wsheet.PageSetup.TopMargin = App.InchesToPoints(top / 2.54);
                        wsheet.PageSetup.LeftMargin = App.InchesToPoints(left / 2.54);
                        wsheet.PageSetup.RightMargin = App.InchesToPoints(right / 2.54);
                        wsheet.PageSetup.BottomMargin = App.InchesToPoints(bottom / 2.54);
                        wsheet.PageSetup.Orientation = XlPageOrientation.xlLandscape;
                        //wsheet.PageSetup.CenterHorizontally = true;
                        //wsheet.PageSetup.CenterVertically = true;
                        return;
                    }
                }

                throw new Exception(String.Format("Can not find sheet '{0}'", SheetName));
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not SetSheetPrntFormat sheet '{0}'" + err.Message, SheetName), err);
            }
        }

        /// <summary>
        /// 重命名一个工作表.
        /// </summary>
        /// <param name="oldName">原来的名字</param>
        /// <param name="newName">新的名字</param>
        public void RenameSheet(string oldName, string newName)
        {
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                foreach (_Worksheet wsheet in App.Sheets)
                {
                    if (String.Compare(wsheet.Name, oldName, true) == 0)
                    {
                        wsheet.Name = newName;
                        return;
                    }
                }
                throw new Exception(String.Format("Can not find sheet '{0}'", oldName));
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not rename sheet '{0}' to '{1}'", oldName, newName), err);
            }
        }

        /// <summary>
        ///重命名当前的工作表.
        /// </summary>
        /// <param name="newName"></param>
        public void RenameCurrentSheet(string newName)
        {
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                (App.ActiveSheet as _Worksheet).Name = newName;
            }
            catch (Exception err)
            {
                throw new Exception(
                    "Can not rename current sheet", err);
            }
        }
        #endregion

        #region Functions to work with cell and range values

        //public object GetValue(string cellAddress, Category category)
        //{
        //    if (String.IsNullOrEmpty(cellAddress))
        //        throw new ArgumentNullException("cellAddress");
        //    if (!AnyFileOpen)
        //        throw new Exception("No file is Open");

        //    try
        //    {
        //        Range range = (App.ActiveSheet as _Worksheet).get_Range(cellAddress, System.Type.Missing);
        //        if (category == Category.Numeric)
        //            return range.Value2;
        //        else
        //            return range.Text;
        //    }
        //    catch (Exception err)
        //    {
        //        throw new Exception(
        //            String.Format(CultureInfo.InvariantCulture, "Can not access values at address '{0}'", cellAddress), err);
        //    }
        //}

        //public double? GetNumericValue(string cellAddress)
        //{
        //    return (double?)GetValue(cellAddress, Category.Numeric);
        //}

        //public object GetFormattedValue(string cellAddress)
        //{
        //    return GetValue(cellAddress, Category.Formatted);
        //}

        private bool IsWasteCellInMergeArea(Range range)
        {
            if (!((bool)range.MergeCells))
                return false;
            Range firstCellInMergeArea = range.MergeArea.Cells[1, 1] as Range;
            return !(range.Column == firstCellInMergeArea.Column && range.Row == firstCellInMergeArea.Row);
        }

        //public ArrayList GetRangeValues(string startCellAddress, string endCellAddress, Category category)
        //{
        //    if (String.IsNullOrEmpty(startCellAddress))
        //        throw new ArgumentNullException("startCellAddress");
        //    if (String.IsNullOrEmpty(endCellAddress))
        //        throw new ArgumentNullException("endCellAddress");
        //    if (!AnyFileOpen)
        //        throw new Exception("No file is Open");

        //    try
        //    {
        //        Range range = App.get_Range(startCellAddress, endCellAddress);

        //        ArrayList arr = new ArrayList();
        //        foreach (Range r in range)
        //        {
        //            if (IsWasteCellInMergeArea(r))
        //                continue;
        //            if (category == Category.Formatted)
        //                arr.Add(r.Text);
        //            else
        //                arr.Add((double?)r.Value2);
        //        }
        //        return arr;
        //    }
        //    catch (Exception err)
        //    {
        //        throw new Exception(
        //            String.Format(CultureInfo.InvariantCulture, "Can not get values of range '{0}:{1}'", startCellAddress, endCellAddress), err);
        //    }
        //}

        //public ArrayList GetRangeFormattedValues(string startCellAddress, string endCellAddress)
        //{
        //    return GetRangeValues(startCellAddress, endCellAddress, Category.Formatted);
        //}

        //public ArrayList GetRangeNumericValues(string startCellAddress, string endCellAddress)
        //{
        //    return GetRangeValues(startCellAddress, endCellAddress, Category.Numeric);
        //}

        public void SetValue(string cellAddress, object value)
        {
            if (String.IsNullOrEmpty(cellAddress))
                throw new ArgumentNullException("cellAddress");
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                App.get_Range(cellAddress, System.Type.Missing).Value2 = value;
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set value of cell '{0}'", cellAddress), err);
            }
        }

        public void SetHyperLinkValue(string cellAddress, string linkAddress, object value)
        {
            if (String.IsNullOrEmpty(cellAddress))
                throw new ArgumentNullException("cellAddress");
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                Range range = App.get_Range(cellAddress, System.Type.Missing);
                range.Value2 = value;
                ((_Worksheet)App.ActiveSheet).Hyperlinks.Add(range, linkAddress);
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set value of cell '{0}'", cellAddress), err);
            }
        }

        public Range GetRange(string cellAccess1, string cellAccess2)
        {
            Range range = null;

            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                range = App.get_Range(cellAccess1, cellAccess2);
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not copy value of cell '{0}'", cellAccess1), err);
            }

            return range;
        }

        /// <summary>
        /// 设置行高
        /// </summary>
        /// <param name="cellFrom1"></param>
        /// <param name="cellTo1"></param>
        public void SetRangeRowHeight(string cellFrom1, string cellTo1, object value)
        {
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                // _Worksheet worksheet = (_Worksheet)App.ActiveWorkbook.Sheets[index];

                App.get_Range(cellFrom1, cellTo1).RowHeight = value;
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not copy value of cell '{0}'", cellFrom1), err);
            }
        }

        /// <summary>
        /// 复制功能
        /// </summary>
        /// <param name="cellFrom1"></param>
        /// <param name="cellTo1"></param>
        public void RangeCopy(string cellFrom1, string cellTo1)
        {
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                // _Worksheet worksheet = (_Worksheet)App.ActiveWorkbook.Sheets[index];

                App.get_Range(cellFrom1).Copy(App.get_Range(cellTo1, cellTo1));
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not copy value of cell '{0}'", cellFrom1), err);
            }
        }


        /// <summary>
        /// 带sheet激活的复制功能
        /// </summary>
        /// <param name="cellFrom1"></param>
        /// <param name="cellFrom2"></param>
        /// <param name="cellTo1"></param>
        /// <param name="cellTo2"></param>
        public void RangeCopy(string cellFrom1, string cellFrom2, string cellTo1, string cellTo2, int index)
        {
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                _Worksheet worksheet = (_Worksheet)App.ActiveWorkbook.Sheets[index];

                App.get_Range(cellFrom1, cellFrom2).Copy(worksheet.get_Range(cellTo1, cellTo2));
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not copy value of cell '{0}'", cellFrom1), err);
            }
        }

        /// <summary>
        /// 剪切一行
        /// </summary>
        /// <param name="FromRowIndex">被剪切的行</param>
        /// <param name="ToRowIndex">粘貼到的行</param>
        public void CutRow(int FromRowIndex, int ToRowIndex)
        {
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                Range range = (Range)App.Rows[FromRowIndex];
                range.Cut((Range)App.Rows[ToRowIndex]);

                range = null;
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set value of cell '{0}'", FromRowIndex), err);
            }
        }

        /// <summary>
        /// 插入一行
        /// </summary>
        /// <param name="RowIndex"></param>
        public void InsertRow(int RowIndex)
        {

            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                Range range = (Range)App.Rows[RowIndex];
                range.Insert();

                range = null;
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set value of cell '{0}'", RowIndex), err);
            }
        }

        /// <summary>
        /// 刪除一行
        /// </summary>
        /// <param name="RowIndex"></param>
        public void DeleteRow(int RowIndex)
        {

            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                Range range = (Range)App.Rows[RowIndex];

                range.Delete();

                range = null;
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set value of cell '{0}'", RowIndex), err);
            }
        }

        /// <summary>
        /// 排序
        /// </summary>
        /// <param name="cellAddress1">Cell's address (for example "A2")</param>
        /// <param name="cellAddress2">Cell's address (for example "A2")</param>
        /// <param name="sortCellAddress1">Cell's sort (for example "A2")</param>
        /// <param name="sortCellAddress2">Cell's sort (for example "A2")</param>
        /// <param name="value">Any desired value</param>
        public void SortRange(string cellAddress1, string cellAddress2, string sortCellAddress1, string sortCellAddress2)
        {

            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                Range rng = App.get_Range(cellAddress1, cellAddress2);
                Range rng1 = App.get_Range(sortCellAddress1, sortCellAddress2);

                rng.Sort(rng1,
                XlSortOrder.xlDescending,
                Type.Missing, Type.Missing,
                XlSortOrder.xlAscending,
                Type.Missing, XlSortOrder.xlDescending,
                XlYesNoGuess.xlNo, Type.Missing, Type.Missing,
                XlSortOrientation.xlSortColumns,
                XlSortMethod.xlPinYin,
                XlSortDataOption.xlSortNormal,
                XlSortDataOption.xlSortNormal,
                XlSortDataOption.xlSortNormal);

            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set value of cell '{0}'", sortCellAddress1), err);
            }
        }

        /// <summary>
        /// Sets a cell content by use given color
        /// </summary>
        /// <param name="cellAddress">Cell's address (for example "A2")</param>
        /// <param name="value">Any desired value</param>
        /// <param name="cl">given color value</param>
        public void SetValue(string cellAddress, object value, int colorIndex)
        {
            if (String.IsNullOrEmpty(cellAddress))
                throw new ArgumentNullException("cellAddress");
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                Range range = null;// 创建一个空的单元格对象

                range = App.get_Range(cellAddress, Missing.Value);// 获取单个单元格
                range.Font.ColorIndex = colorIndex;      // 设置字体颜色

                range.Value2 = value;// 设置单元格的值
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set value of cell '{0}'", cellAddress), err);
            }
        }

        public void SetValue(int rowindex, int colindex, object value)
        {
            if (rowindex == null || colindex == null)
                throw new ArgumentNullException("cellAddress");
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                App.Cells[rowindex, colindex] = value;
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set value of cell '{0}'", rowindex.ToString() + colindex.ToString()), err);
            }
        }

        /// <summary>
        ///清理rangge.
        /// </summary>
        /// <param name="cellAddress1">Cell's address (for example "A2")</param>
        /// <param name="cellAddress2">Cell's address (for example "A2")</param>
        public void RangeClear(string cellAddress1, string cellAddress2)
        {
            if (String.IsNullOrEmpty(cellAddress1))
                throw new ArgumentNullException("cellAddress1");
            if (String.IsNullOrEmpty(cellAddress2))
                throw new ArgumentNullException("cellAddress2");
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                App.get_Range(cellAddress1, cellAddress2).Clear();
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set NumberFormat of cell '{0}'", cellAddress1), err);
            }
        }

        /// <summary>
        /// 设置一个cell的边线.
        /// </summary>
        /// <param name="cellAddress1">Cell's address (for example "A2")</param>
        /// <param name="cellAddress2">Cell's address (for example "A2")</param>
        /// <param name="value">Any desired value</param>
        public void SetBorderColor(string cellAddress1, string cellAddress2, object value)
        {
            if (String.IsNullOrEmpty(cellAddress1))
                throw new ArgumentNullException("cellAddress1");
            if (String.IsNullOrEmpty(cellAddress2))
                throw new ArgumentNullException("cellAddress2");
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                App.get_Range(cellAddress1, cellAddress2).Borders.Color = value;
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set NumberFormat of cell '{0}'", cellAddress1), err);
            }
        }

        public void SetNumberFormat(string cellAddress, object value)
        {
            if (String.IsNullOrEmpty(cellAddress))
                throw new ArgumentNullException("cellAddress");
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {

                App.get_Range(cellAddress, System.Type.Missing).NumberFormat = value;
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set NumberFormat of cell '{0}'", cellAddress), err);
            }
        }

        public void CellMerge(string cellAddress1, string cellAddress2)
        {
            if (String.IsNullOrEmpty(cellAddress1))
                throw new ArgumentNullException("cellAddress1");
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                Range range = App.get_Range(cellAddress1, cellAddress2);
                range.Merge(range.MergeCells);
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set NumberFormat of cell '{0}'", cellAddress1), err);
            }
        }

        public void SetColumnAutoFit(string cellAddress)
        {
            if (String.IsNullOrEmpty(cellAddress))
                throw new ArgumentNullException("cellAddress");
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {

                App.get_Range(cellAddress, System.Type.Missing).EntireColumn.AutoFit();
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set NumberFormat of cell '{0}'", cellAddress), err);
            }
        }

        public void SetColumnWidth(string cellAddress, object value)
        {
            if (String.IsNullOrEmpty(cellAddress))
                throw new ArgumentNullException("cellAddress");
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {

                App.get_Range(cellAddress).ColumnWidth = value;
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set NumberFormat of cell '{0}'", cellAddress), err);
            }
        }

        public void SetFontSize(string cellAddress, object value)
        {
            if (String.IsNullOrEmpty(cellAddress))
                throw new ArgumentNullException("cellAddress");
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {

                App.get_Range(cellAddress, System.Type.Missing).Font.Size = value;
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set NumberFormat of cell '{0}'", cellAddress), err);
            }
        }

        public void MergeAndWriteValue(string startCellAddress, string endCellAddress, object value, int colorIndex)
        {
            if (String.IsNullOrEmpty(startCellAddress))
                throw new ArgumentNullException("startCellAddress");
            if (String.IsNullOrEmpty(endCellAddress))
                throw new ArgumentNullException("endCellAddress");
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                Range range = null;// 创建一个空的单元格对象

                range = App.get_Range(startCellAddress, endCellAddress);// 获取多个单元格
                range.Merge(Missing.Value);
                // 设置单元格左边框加粗
                range.Borders[XlBordersIndex.xlEdgeLeft].Weight = XlBorderWeight.xlThick;
                // 设置单元格右边框加粗
                range.Borders[XlBordersIndex.xlEdgeRight].Weight = XlBorderWeight.xlThick;
                range.HorizontalAlignment = XlHAlign.xlHAlignCenter;// 设置单元格水平居中
                range.VerticalAlignment = XlVAlign.xlVAlignCenter;// 设置单元格垂直居中

                range.Borders.LineStyle = 1;    // 设置单元格边框
                range.Font.Bold = true;         // 加粗字体
                range.Font.Size = 12;           // 设置字体大小
                range.Font.ColorIndex = colorIndex;      // 设置字体颜色
                range.Interior.ColorIndex = 33;  // 设置单元格背景色

                range.Value2 = value;

            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set values of range '{0}:{1}'", startCellAddress, endCellAddress), err);
            }
        }

        public void SetRangeValue(string startCellAddress, string endCellAddress, object value)
        {
            if (String.IsNullOrEmpty(startCellAddress))
                throw new ArgumentNullException("startCellAddress");
            if (String.IsNullOrEmpty(endCellAddress))
                throw new ArgumentNullException("endCellAddress");
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                App.get_Range(startCellAddress, endCellAddress).Value2 = value;
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set values of range '{0}:{1}'", startCellAddress, endCellAddress), err);
            }
        }

        public void SetRangeValues(string startCellAddress, string endCellAddress, IList values)
        {
            if (values == null)
                throw new ArgumentNullException("values");
            if (String.IsNullOrEmpty(startCellAddress))
                throw new ArgumentNullException("startCellAddress");
            if (String.IsNullOrEmpty(endCellAddress))
                throw new ArgumentNullException("endCellAddress");
            if (!AnyFileOpen)
                throw new Exception("No file is Open");

            try
            {
                int index = 0;
                Range range = App.get_Range(startCellAddress, endCellAddress);
                foreach (Range r in range)
                {
                    if (index >= values.Count)
                        return;
                    if (IsWasteCellInMergeArea(r))
                        continue;
                    r.Value2 = values[index];
                    index++;
                }
            }
            catch (Exception err)
            {
                throw new Exception(
                    String.Format(CultureInfo.InvariantCulture, "Can not set values of range '{0}:{1}'", startCellAddress, endCellAddress), err);
            }
        }
        #endregion

        #region IDisposable Members

        private bool _disposedValue; // To detect redundant calls

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }

        [DllImport("User32.dll", CharSet = CharSet.Auto)]
        public static extern int GetWindowThreadProcessId(IntPtr hwnd, out int ID);

        protected virtual void Dispose(bool disposing)
        {

            if (!_disposedValue)
                if (disposing)
                {
                    if (App != null)
                    {
                        Close();
                        //App.Quit();
                        //App = null;
                        //instance = null;
                        //GC.Collect();
                        IntPtr t = new IntPtr(App.Hwnd); //得到这个句柄，具体作用是得到这块内存入口 
                        int k = 0;
                        GetWindowThreadProcessId(t, out k); //得到本进程唯一标志k 
                        System.Diagnostics.Process p = System.Diagnostics.Process.GetProcessById(k); //得到对进程k的引用 
                        p.Kill(); //关闭进程k 

                        App = null;
                        instance = null;

                        Thread.CurrentThread.CurrentCulture = OriginalCulture;
                    }
                }

            _disposedValue = true;
        }

        #endregion
    }
}
