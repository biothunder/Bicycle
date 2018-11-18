# Bicycle
Bicycle App Demo

##A.微調 gyroscope 的方式與介面:

  參考陀螺儀提供的 ***X軸旋轉角度*** 與 ***Z軸旋轉角度*** 來做為馬達助力圖形傾斜的角度

B.接收速度數據的介面:

  由MainViewModel通過ObserveSpeedInteract來取得json data中的速度資料，
  每600毫秒讀出一次速度的值

C.接收馬達助力的介面:

  這部分先使用加速度的值來做來馬達助力，隨著速度改變來決定加速度的值，
  當加速度為正值時，馬達助力圖形會上升，反之則下降，
  另外也會接收陀螺儀提供的旋轉角度做傾斜的變化

D.接收踩踏 RPM 的介面:

  隨著速度來改變動畫的速度及透明度

E.將附件中的兩組 sample 數據輸入，產生UI作動:

  兩組數據是存放在assets資料夾中的test_data_1、test_data_2，
  可在 ***ReadJsonDataService.class*** 中第58行改變數據輸入來源，
  數據輸入來源名稱包括C.class中的 ***TEST_DATA_1_NAME*** 、 ***TEST_DATA_2_NAME*** ，
  以及自己準備的測試空資料 ***TEST_DATA_EMPTY_NAME***
