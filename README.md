# group5-shopping

## 前言-資策會學習與專題開發心得

這是我在中壢資策會與夥伴開發的專案，主要是聚焦在後端 Java 技術的實作。我主要是負責購物商城功能(不包含會員註冊)，後台網頁對商品進行資料庫增刪改查，與前台網頁將商品加入購物車完成訂單。

---

在中壢資策會學習接觸軟體工程師領域後，才發現這個世界非常的大，從規劃專案到開始開發都有其專門依循的方式。

敏捷軟體開發、UML、UseCase 討論、活動圖討論、KANO 問卷調查、討論資料庫的 ER Model 並將其正規化，到後面還有接觸 AWS、Git 版控與程式測試，所接觸的新資訊實在多到不可思議。

最重要的是還要能將資料庫、前端與後端的技術熟練，過程實在是遇到不少挫折。

到了專題專案開發階段，有不懂的就盡量請教同學與老師，積極與夥伴互相溝通和鼓勵，最後還是順利完成專題並上台發表報告。

---

課程最後階段，也教了框架Hibernate、Spring、SpringMVC、Struts 2，但很可惜這些框架技術並沒有應用在這次專題上。雖然在資策會上課這段過程花費不少心力，但接觸到許多新知識倒也覺得充實。目前已開始將專題練習應用 Hibernate 、SpringORM、SpringMVC 等技術持續學習精進。

---

## 📑目錄

| 📄                           | 📖                | 🗃          | 🖥             | 🖥             | 📊           | 🔎              |
|:----------------------------:|:-----------------:|:-----------:|:--------------:|:--------------:|:------------:|:---------------:|
| [專案簡述與 Source code 位置目錄](#1) | [購物 ER Model](#2) | [DB 欄位](#3) | [前台網頁畫面展示](#4) | [後台網頁畫面展示](#5) | [Git 版控](#6) | [Hibernate](#7) |

| 🔎              | 🔎              |
|:---------------:|:---------------:|
| [SpringORM](#8) | [SpringMVC](#9) |

## <span id="1">📄1.專案簡述與 Source code 位置目錄</span>

- 專案使用到的技術
  
  - Java
  
  - JDBC
  
  - Servlet
  
  - JSP
  
  - JavaScript
  
  - jQuery
  
  - HTML
  
  - CSS
  
  - AJAX
  
  - Bootstrap
  
  - Sublime Text 3
  
  - IDE - Eclipse
  
  - Server - Tomcat v9.0
  
  - Database - Oracle

- 專案架構
  
  - 使用 MVC 設計模式
  
  - Model 部分使用 Facade 設計模式

- Source code 位置目錄
  
  - [src/com](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com)
    
    - [product](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/product)
      
      - [controller](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/product/controller)
      
      - [model](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/product/model)
    
    - [product_category](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/product_category)
      
      - [controller](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/product_category/controller)
      
      - [model](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/product_category/model)
    
    - [product_image](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/product_image)
      
      - [controller](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/product_image/controller)
      
      - [model](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/product_image/model)
    
    - [product_order](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/product_order)
      
      - [controller](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/product_order/controller)(結帳產生訂單Code位置)
      
      - [model](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/product_order/model)
    
    - [order_details](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/order_details)
      
      - [controller](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/order_details/controller)
      
      - [model](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/order_details/model)
    
    - [shopping](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/shopping/controller)
      
      - [controller](https://github.com/Lewis-Tseng/group5-shopping/tree/master/src/com/shopping/controller)(購物車Code位置)

- 網頁位置目錄
  
  - [WebContent](https://github.com/Lewis-Tseng/group5-shopping/tree/master/WebContent)
    
    - [back_end](https://github.com/Lewis-Tseng/group5-shopping/tree/master/WebContent/back_end)
      
      - [product](https://github.com/Lewis-Tseng/group5-shopping/tree/master/WebContent/back_end/product)
      
      - [product_category](https://github.com/Lewis-Tseng/group5-shopping/tree/master/WebContent/back_end/product_category)
      
      - [product_image](https://github.com/Lewis-Tseng/group5-shopping/tree/master/WebContent/back_end/product_image)
      
      - [product_order](https://github.com/Lewis-Tseng/group5-shopping/tree/master/WebContent/back_end/product_order)
      
      - [order_details](https://github.com/Lewis-Tseng/group5-shopping/tree/master/WebContent/back_end/order_details)
    
    - [front_end](https://github.com/Lewis-Tseng/group5-shopping/tree/master/WebContent/front_end)
      
      - [product_front](https://github.com/Lewis-Tseng/group5-shopping/tree/master/WebContent/front_end/product_front)(購物車JSP位置)
      
      - [product_order_front](https://github.com/Lewis-Tseng/group5-shopping/tree/master/WebContent/front_end/product_order_front)

## <span id="2">📖2.購物 ER Model</span>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/ERModel_shopping/%E8%B3%BC%E7%89%A9%E5%95%86%E5%9F%8EER%20Model.JPG" width="800"/> </div>

## <span id="3">🗃3. DB 欄位</span>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/DBColumn_shopping/%E5%95%86%E5%93%81%20%E8%A8%82%E5%96%AE%20%E8%A8%82%E5%96%AE%E6%98%8E%E7%B4%B0%E6%AC%84%E4%BD%8D.JPG" width="800"/> </div>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/DBColumn_shopping/%E5%95%86%E5%93%81%E5%9C%96%E7%89%87%20%E5%95%86%E5%93%81%E9%A1%9E%E5%88%A5%E6%AC%84%E4%BD%8D.JPG" width="800"/> </div>

## <span id="4">🖥4.前台網頁畫面展示</span>

#### 4-1.購物商城首頁

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E8%B3%BC%E7%89%A9%E5%95%86%E5%9F%8E%E9%A6%96%E9%A0%81.JPG" width="800"/> </div>

- 練習用 Java 8 Lambda 語法使後面加入的商品能在最前面陳列

```java
public List<ProductVO> getAllPro_StaisZero() {
        List<ProductVO> list = dao.getAllPro_StaisZero();
        list = list.stream().filter(p -> p.getPro_sta().equals("0")).collect(Collectors.toList());
        Collections.reverse(list);
        return list;
}
```

#### 4-2.點擊加入購物車按鈕，快速加入商品

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E9%A6%96%E9%A0%81%E9%BB%9E%E6%93%8A%E5%8A%A0%E5%85%A5%E8%B3%BC%E7%89%A9%E8%BB%8A.jpg" width="800"/> </div>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E5%8A%A0%E5%85%A5%E8%B3%BC%E7%89%A9%E8%BB%8A%E6%88%90%E5%8A%9F.JPG" width="800"/> </div>

- 購物商城首頁使用 AJAX 直接將商品加入購物車

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

#### 4-3.進入商品頁面，調整數量加入購物車

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E5%95%86%E5%93%81%E9%A0%81%E9%9D%A2%E8%AA%BF%E6%95%B4%E6%95%B8%E9%87%8F%E5%8A%A0%E5%85%A5%E8%B3%BC%E7%89%A9%E8%BB%8A.JPG" witth="800"/> </div>

#### 4-4.修改購物車內商品數量與刪除

- 目前最下面商品數量為 3

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E9%80%B2%E5%85%A5%E8%B3%BC%E7%89%A9%E8%BB%8A%E9%A0%81%E9%9D%A2.JPG" width="800"/> </div>

- 修改最下面商品數量為 1

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E4%BF%AE%E6%94%B9%E5%95%86%E5%93%81%E6%95%B8%E9%87%8F.JPG" width="800"/> </div>

- 刪除最上方商品

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E5%88%AA%E9%99%A4%E5%95%86%E5%93%81.JPG" width="800"/> </div>

- 購物車新增刪除 Code 片段

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

- [購物車 Code 連結 controller/Shopping_ProductServlet](https://github.com/Lewis-Tseng/group5-shopping/blob/master/src/com/shopping/controller/Shopping_ProductServlet.java)

#### 4-5.商品結帳頁面

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E5%95%86%E5%93%81%E7%B5%90%E5%B8%B3%E9%A0%81%E9%9D%A2-%E4%BD%BF%E7%94%A8%E9%BB%9E%E6%95%B8%E6%89%A3%E6%AC%BE.JPG" width="800"/> </div>

#### 4-6.結帳完成並產生訂單

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E7%94%A2%E7%94%9F%E8%B3%BC%E7%89%A9%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

- 新增訂單與明細片段 Code

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
                    /*開始新增訂單明細取值且加入List*/
```

- [訂單 Code 連結 controller/Product_OrderServlet](https://github.com/Lewis-Tseng/group5-shopping/blob/master/src/com/product_order/controller/Product_OrderServlet.java)

- [訂單 Code 連結 model/Product_OrderDAO](https://github.com/Lewis-Tseng/group5-shopping/blob/master/src/com/product_order/model/Product_OrderDAO.java)

#### 4-7.搜尋訂單

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

- 用 include 插入搜尋到的訂單

```java
<%if (request.getAttribute("listMember_Orders_ByCompositeQuery") != null){%>
                         <jsp:include page="Member_ListOrder_include.jsp" />
                       <%}%>
```

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE%E5%87%BA%E7%8F%BE%E4%B8%80%E7%AD%86%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

## <span id="5">🖥5.後台網頁畫面展示</span>

#### 5-1.購物商城管理首頁

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%BE%8C%E5%8F%B0images/%E8%B3%BC%E7%89%A9%E5%95%86%E5%9F%8E%E7%AE%A1%E7%90%86.JPG" width="800"/> </div>

#### 5-2.新增商品類別

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%BE%8C%E5%8F%B0images/%E6%96%B0%E5%A2%9E%E5%95%86%E5%93%81%E9%A1%9E%E5%88%A5.JPG" width="800"/> </div>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%BE%8C%E5%8F%B0images/%E6%96%B0%E5%A2%9E%E5%95%86%E5%93%81%E9%A1%9E%E5%88%A5%E6%88%90%E5%8A%9F.JPG" width="800"/> </div>

- 新增商品類別 Code 片段

```java
if ("insert".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();

            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
                String cat_nam = req.getParameter("cat_nam");
                if (cat_nam == null || cat_nam.trim().length() == 0) {
                    errorMsgs.add("商品類別名稱: 請勿空白");
                }

                Product_CategoryVO product_categoryVO = new Product_CategoryVO();
                product_categoryVO.setCat_nam(cat_nam);

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

#### 5-3.新增商品

- 輸入商品名稱

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%BE%8C%E5%8F%B0images/%E6%96%B0%E5%A2%9E%E5%95%86%E5%93%81%E5%9C%96%E7%89%87%E9%A0%90%E8%A6%BD.JPG" width="800"/> </div>

- 選擇商品狀態為下架，類別為剛新增成功的類別

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%BE%8C%E5%8F%B0images/%E6%96%B0%E5%A2%9E%E5%95%86%E5%93%81%E7%8B%80%E6%85%8B%E4%B8%8B%E6%9E%B6.JPG" width="800"/> </div>

#### 5-4.新增商品圖片

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%BE%8C%E5%8F%B0images/%E6%96%B0%E5%A2%9E%E5%95%86%E5%93%81%E5%9C%96%E7%89%87%E9%A0%90%E8%A6%BD.JPG" width="800"/> </div>

#### 5-5.將商品上架

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%BE%8C%E5%8F%B0images/%E5%95%86%E5%93%81%E4%B8%8A%E6%9E%B6.JPG" width="800"/> </div>

#### 5-6.回前台商城首頁確認已上架

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%BE%8C%E5%8F%B0images/%E5%95%86%E5%93%81%E5%B7%B2%E4%B8%8A%E6%9E%B6%E8%87%B3%E8%B3%BC%E7%89%A9%E5%95%86%E5%9F%8E%E9%A6%96%E9%A0%81.JPG" width="800"/> </div>

#### 5-7.也可收搜尋到新上架商品且可購買

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%BE%8C%E5%8F%B0images/%E6%90%9C%E5%B0%8BJava%E5%95%86%E5%93%81.JPG" width="800"/> </div>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%BE%8C%E5%8F%B0images/%E7%A2%BA%E8%AA%8D%E6%96%B0%E4%B8%8A%E6%9E%B6%E5%95%86%E5%93%81%E4%B9%9F%E8%83%BD%E8%B3%BC%E8%B2%B7%E7%94%A2%E7%94%9F%E8%A8%82%E5%96%AE%E8%88%87%E6%98%8E%E7%B4%B0.JPG" width="800"/> </div>

## <span id="6">📊6. Git 版控</span>

#### 6-1.目前專案已開始用 Git 做版本控管

資策會時期並無導入Git版控，此為後續自學練習。

- Git Terminal

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/Git%E7%89%88%E6%8E%A7images/Git_Terminal.JPG" width="800"/> </div>

- GUI 使用 SourceTree 輔助

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/Git%E7%89%88%E6%8E%A7images/%E4%BD%BF%E7%94%A8%E7%9A%84GUI_1.JPG" width="800"/> </div>

- 流程走 Git-flow

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/Git%E7%89%88%E6%8E%A7images/%E6%B5%81%E7%A8%8B%E8%B5%B0Git-flow_1.JPG" width="800"/> </div>

## <span id="7">🔎7. Hibernate</span>

- 目前專案已開始練習改用 Hibernate 框架 (此版本目前存放在 [feature branch](https://github.com/Lewis-Tseng/group5-shopping/tree/feature/%E6%B8%AC%E8%A9%A6%E8%A8%82%E5%96%AE%E8%88%87%E6%98%8E%E7%B4%B0%E8%A4%87%E5%90%88%E4%B8%BB%E9%8D%B5%E8%83%BD%E5%90%A6%E6%94%B9%E6%88%90JPA%E6%A8%99%E7%B1%A4%E7%89%88%E5%AE%8C%E6%88%90%E6%96%B0%E5%A2%9E) )，
  
  但有碰到問題尚未解決，訂單明細複合主鍵其中一個是資料庫的自增主鍵值，無法用級聯同時新增訂單與明細。
  
  網路上搜尋，可能需要使用 Spring 提共的 Map getKeys() 方法，從應用層去抓值？

- 實體映射檔部分使用 xml 與  JPA  Annotations 混合練習使用

- Hibernate 部分 Source code 位置
  
  - [hibernate.cfg.xml](https://github.com/Lewis-Tseng/group5-shopping/blob/feature/%E6%B8%AC%E8%A9%A6%E8%A8%82%E5%96%AE%E8%88%87%E6%98%8E%E7%B4%B0%E8%A4%87%E5%90%88%E4%B8%BB%E9%8D%B5%E8%83%BD%E5%90%A6%E6%94%B9%E6%88%90JPA%E6%A8%99%E7%B1%A4%E7%89%88%E5%AE%8C%E6%88%90%E6%96%B0%E5%A2%9E/src/hibernate.cfg.xml "hibernate.cfg.xml")
  
  - [hibernate/util](https://github.com/Lewis-Tseng/group5-shopping/tree/feature/%E6%B8%AC%E8%A9%A6%E8%A8%82%E5%96%AE%E8%88%87%E6%98%8E%E7%B4%B0%E8%A4%87%E5%90%88%E4%B8%BB%E9%8D%B5%E8%83%BD%E5%90%A6%E6%94%B9%E6%88%90JPA%E6%A8%99%E7%B1%A4%E7%89%88%E5%AE%8C%E6%88%90%E6%96%B0%E5%A2%9E/src/hibernate/util "This path skips through empty directories")
  
  - [product.hbm.xml](https://github.com/Lewis-Tseng/group5-shopping/blob/feature/%E6%B8%AC%E8%A9%A6%E8%A8%82%E5%96%AE%E8%88%87%E6%98%8E%E7%B4%B0%E8%A4%87%E5%90%88%E4%B8%BB%E9%8D%B5%E8%83%BD%E5%90%A6%E6%94%B9%E6%88%90JPA%E6%A8%99%E7%B1%A4%E7%89%88%E5%AE%8C%E6%88%90%E6%96%B0%E5%A2%9E/src/com/product/model/product.hbm.xml "product.hbm.xml")
  
  - [ProductDAO.java](https://github.com/Lewis-Tseng/group5-shopping/blob/feature/%E6%B8%AC%E8%A9%A6%E8%A8%82%E5%96%AE%E8%88%87%E6%98%8E%E7%B4%B0%E8%A4%87%E5%90%88%E4%B8%BB%E9%8D%B5%E8%83%BD%E5%90%A6%E6%94%B9%E6%88%90JPA%E6%A8%99%E7%B1%A4%E7%89%88%E5%AE%8C%E6%88%90%E6%96%B0%E5%A2%9E/src/com/product/model/ProductDAO.java "ProductDAO.java")
  
  - [Product_OrderVO.java](https://github.com/Lewis-Tseng/group5-shopping/blob/feature/%E6%B8%AC%E8%A9%A6%E8%A8%82%E5%96%AE%E8%88%87%E6%98%8E%E7%B4%B0%E8%A4%87%E5%90%88%E4%B8%BB%E9%8D%B5%E8%83%BD%E5%90%A6%E6%94%B9%E6%88%90JPA%E6%A8%99%E7%B1%A4%E7%89%88%E5%AE%8C%E6%88%90%E6%96%B0%E5%A2%9E/src/com/product_order/model/Product_OrderVO.java "Product_OrderVO.java")
  
  - [Order_DetailsVO.java](https://github.com/Lewis-Tseng/group5-shopping/blob/feature/%E6%B8%AC%E8%A9%A6%E8%A8%82%E5%96%AE%E8%88%87%E6%98%8E%E7%B4%B0%E8%A4%87%E5%90%88%E4%B8%BB%E9%8D%B5%E8%83%BD%E5%90%A6%E6%94%B9%E6%88%90JPA%E6%A8%99%E7%B1%A4%E7%89%88%E5%AE%8C%E6%88%90%E6%96%B0%E5%A2%9E/src/com/order_details/model/Order_DetailsVO.java "Order_DetailsVO.java")

## <span id="8">🔎8. SpringORM</span>

- 導入SpringORM模組接管Hibernate (此版本目前存放在 [feature branch](https://github.com/Lewis-Tseng/group5-shopping/tree/feature/%E4%BD%BF%E7%94%A8SpringORM%E6%A8%A1%E7%B5%84%E6%95%B4%E5%90%88%E6%8E%A5%E7%AE%A1Hibernate) )

- SpringORM 部分 Source code位置
  
  - [model-config-JndiObjectFactoryBean.xml](https://github.com/Lewis-Tseng/group5-shopping/blob/feature/%E4%BD%BF%E7%94%A8SpringORM%E6%A8%A1%E7%B5%84%E6%95%B4%E5%90%88%E6%8E%A5%E7%AE%A1Hibernate/src/model-config-JndiObjectFactoryBean.xml)
  
  - [ProductDAO 使用 HibernateTemplate](https://github.com/Lewis-Tseng/group5-shopping/blob/feature/%E4%BD%BF%E7%94%A8SpringORM%E6%A8%A1%E7%B5%84%E6%95%B4%E5%90%88%E6%8E%A5%E7%AE%A1Hibernate/src/com/product/model/ProductDAO.java)

## <span id="9">🔎8. SpringMVC</span>

- 導入SpringMVC Controller (此版本目前存放在 [feature branch](https://github.com/Lewis-Tseng/group5-shopping/tree/feature/%E5%B0%8E%E5%85%A5SpringMVC%E6%B8%AC%E8%A9%A6/src) )

- SpringMVC 部分 Source code 位置
  
  - [model-config-JndiObjectFactoryBean.xml 包含交易部分設定](https://github.com/Lewis-Tseng/group5-shopping/blob/feature/%E5%B0%8E%E5%85%A5SpringMVC%E6%B8%AC%E8%A9%A6/src/model-config-JndiObjectFactoryBean.xml)
  
  - [註冊前端控制器與加入組態設定檔案](https://github.com/Lewis-Tseng/group5-shopping/tree/feature/%E5%B0%8E%E5%85%A5SpringMVC%E6%B8%AC%E8%A9%A6/src/spring5/mvc/configuration)
  
  - [ProductSpringController.java 採用 Bean 驗證](https://github.com/Lewis-Tseng/group5-shopping/blob/feature/%E5%B0%8E%E5%85%A5SpringMVC%E6%B8%AC%E8%A9%A6/src/com/product/controller/ProductSpringController.java)
  
  - [ProductSpringController2.java 方法內驗證](https://github.com/Lewis-Tseng/group5-shopping/blob/feature/%E5%B0%8E%E5%85%A5SpringMVC%E6%B8%AC%E8%A9%A6/src/com/product/controller/ProductSpringController2.java)
  
  - [Product_OrderDAO.java](https://github.com/Lewis-Tseng/group5-shopping/blob/feature/%E5%B0%8E%E5%85%A5SpringMVC%E6%B8%AC%E8%A9%A6/src/com/product_order/model/Product_OrderDAO.java)
  