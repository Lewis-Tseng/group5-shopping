# group5-shopping

| [Code目錄位置](#1) | [ER Model](#2) | [DB欄位](#3) | [前台網頁](#4) | [後台網頁](#5) | [Git版控](#6) |
| -------------- |:--------------:| ---------- | ---------- | ---------- | ----------- |

## <span id="1">1.Code目錄位置</span>

## <span id="2">2.ER Model</span>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E8%B3%BC%E7%89%A9%E5%95%86%E5%9F%8E%E9%A6%96%E9%A0%81.JPG" width="800"/> </div>

## <span id="3">3.DB欄位</span>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E8%B3%BC%E7%89%A9%E5%95%86%E5%9F%8E%E9%A6%96%E9%A0%81.JPG" width="800"/> </div>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E8%B3%BC%E7%89%A9%E5%95%86%E5%9F%8E%E9%A6%96%E9%A0%81.JPG" width="800"/> </div>

## <span id="4">4.前台網頁</span>

###### 4-1.購物商城首頁

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E8%B3%BC%E7%89%A9%E5%95%86%E5%9F%8E%E9%A6%96%E9%A0%81.JPG" width="800"/> </div>

###### 4-2.點擊加入購物車按鈕，快速加入商品

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E9%A6%96%E9%A0%81%E9%BB%9E%E6%93%8A%E5%8A%A0%E5%85%A5%E8%B3%BC%E7%89%A9%E8%BB%8A.jpg" width="800"/> </div>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E5%8A%A0%E5%85%A5%E8%B3%BC%E7%89%A9%E8%BB%8A%E6%88%90%E5%8A%9F.JPG" width="800"/> </div>

- 購物商城首頁使用Ajax頁面直接加入購物車

```js
$('button[class*="send"]').click(function(){

                                var prono = $(this).attr('prono');
                                var select = "input[class=\""+prono+"\"]";
                                var quantity =$(select).val();

                            $.ajax({
                                url:"<%=request.getContextPath()%>/front_end/shopping_product/shopping_product.do",
                                type:"post",
                                data:{
                                    action : "ADD",
                                    pro_quantity : quantity,
                                    pro_no : prono
                                },
                                success:function(){
                                    Swal.fire(
                                            "\""+ quantity +"\"個商品加入購物車成功!",
                                            "Thank you",
                                            'success'
                                            )
                                    }
                            });
                            });
```

###### 4-3.進入商品頁面，調整數量加入購物車

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E5%95%86%E5%93%81%E9%A0%81%E9%9D%A2%E8%AA%BF%E6%95%B4%E6%95%B8%E9%87%8F%E5%8A%A0%E5%85%A5%E8%B3%BC%E7%89%A9%E8%BB%8A.JPG" witth="800"/> </div>

###### 4-4.修改購物車內商品數量與刪除

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E9%80%B2%E5%85%A5%E8%B3%BC%E7%89%A9%E8%BB%8A%E9%A0%81%E9%9D%A2.JPG" width="800"/> </div>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E4%BF%AE%E6%94%B9%E5%95%86%E5%93%81%E6%95%B8%E9%87%8F.JPG" width="800"/> </div>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E5%88%AA%E9%99%A4%E5%95%86%E5%93%81.JPG" width="800"/> </div>

- 購物車新增刪除Code片段

```java
int oldbuylist = 0;//判斷要跳回購物車頁面或是商品首頁用
        if(buylist != null ) {
            oldbuylist = buylist.size();
        } 

        if (!action.equals("CHECKOUT")) {

            // 刪除購物車中的書籍
            if (action.equals("DELETE")) {
                String del = req.getParameter("del");
                int d = Integer.parseInt(del);
                buylist.remove(d);
            }
            // 新增書籍至購物車中
            else if (action.equals("ADD")) {
                /*購物車內修改數量 抓到index數值*/
                String add = req.getParameter("add");
                if(add != null) {
                int ad = Integer.parseInt(add);

                Integer pro_quantity_new = null;
                try {
                    pro_quantity_new = new Integer(req.getParameter("pro_quantity_new").trim());
                } catch (NumberFormatException e) {
                    pro_quantity_new = 0;

                }

                Order_Details_ProductVO od_productVO_change = (Order_Details_ProductVO) buylist.get(ad);
                od_productVO_change.setPro_quantity(pro_quantity_new);
                buylist.remove(ad);
                buylist.add(ad, od_productVO_change);

                session.setAttribute("shoppingcart", buylist);
                String url = "/front_end/product_front/Product_Cart.jsp";
                RequestDispatcher rd = req.getRequestDispatcher(url);
                rd.forward(req, res);
                return;
                }
                /*購物車內修改數量 抓到index數值*/
```

#### [購物車完整CodeLink](https://github.com/Lewis-Tseng/group5-shopping/blob/master/src/com/shopping/controller/Shopping_ProductServlet.java)

###### 4-5.商品結帳頁面

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E5%95%86%E5%93%81%E7%B5%90%E5%B8%B3%E9%A0%81%E9%9D%A2-%E4%BD%BF%E7%94%A8%E9%BB%9E%E6%95%B8%E6%89%A3%E6%AC%BE.JPG" width="800"/> </div>

###### 4-6.結帳完成並產生訂單

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E7%94%A2%E7%94%9F%E8%B3%BC%E7%89%A9%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

- 新增訂單與明細片段Code

```java
  /*結帳產生訂單*/
            if ("payment".equals(action)) {

                HttpSession session = req.getSession();
                List<String> errorMsgs = new LinkedList<String>();
                req.setAttribute("errorMsgs", errorMsgs);

                @SuppressWarnings("unchecked")
                List<Order_Details_ProductVO> od_buylist = (Vector<Order_Details_ProductVO>) session
                        .getAttribute("shoppingcart");

                try {
                    /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

                    /*開始新增訂單明細取值且加入List*/
                    List<Order_DetailsVO> od_colist = new ArrayList<Order_DetailsVO>();

                    Integer total = 0;
                    Integer pro_total = 0;

                    if (od_buylist.size() != 0) {
                        for (Order_Details_ProductVO odpVO : od_buylist) {
                            /* 從購物車商品取值給訂單明細用 */
                            Order_DetailsVO order_detailsVO = new Order_DetailsVO();
                            order_detailsVO.setPro_no(odpVO.getPro_no());
                            order_detailsVO.setQuantity(odpVO.getPro_quantity());
                            order_detailsVO.setUni_pri(odpVO.getPro_pri());
                            od_colist.add(order_detailsVO);
                            /* 從購物車商品取值給訂單總金額和總數量用 */
                            Integer price = odpVO.getPro_pri();
                            Integer quantity = odpVO.getPro_quantity();
                            total += (price * quantity);
                            pro_total += quantity;
                        }
                    } else {
                        errorMsgs.add("購物車沒有東西");
                    }
                    /*開始新增訂單明細取值且加入List*/車沒有東西");
                    }
                    /* 訂單明細新增完成 */
```

#### [交易行為新增訂單與明細完整CodeLink](https://github.com/Lewis-Tseng/group5-shopping/blob/master/src/com/product_order/controller/Product_OrderServlet.java)

###### 4-7.搜尋訂單

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE%E5%87%BA%E7%8F%BE%E4%B8%80%E7%AD%86%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

==================================================================

## <span id="5">5.後台網頁</span>

###### 5-1.購物商城管理首頁

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

###### 5-2.新增商品類別

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

- 新增商品類別Code片段

```java
if ("insert".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
                String cat_nam = req.getParameter("cat_nam");
                if (cat_nam == null || cat_nam.trim().length() == 0) {
                    errorMsgs.add("商品類別名稱: 請勿空白");
                }

                Product_CategoryVO product_categoryVO = new Product_CategoryVO();
                product_categoryVO.setCat_nam(cat_nam);

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("product_categoryVO", product_categoryVO); 
                    RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_category/addProduct_Category.jsp");
                    failureView.forward(req, res);
                    return;
                }

                /*************************** 2.開始新增資料 ***************************************/
                Product_CategoryService product_categorySvc = new Product_CategoryService();
                product_categoryVO = product_categorySvc.addProduct_Category(cat_nam);
                /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
                String url = "/back_end/product_category/listAllProduct_Category.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAll.jsp
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 **********************************/
            } catch (Exception e) {
                errorMsgs.add(e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product_category/addProduct_Category.jsp");
                failureView.forward(req, res);
            }
        }
```

###### 5-3.新增商品(商品狀態為下架)

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

###### 5-4.新增商品圖片

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

###### 5-5.將商品上架

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

#### [後台Code連結]()

###### 5-6.回前台商城首頁確認已上架且可購買

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

=====================================================================

## <span id="2">6.Git版控</span>

###### 6-1.目前練習的專案已開始用Git做版本控管

- GUI使用SourceTree輔助

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

- 流程走Git-flow

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

===========================================================
