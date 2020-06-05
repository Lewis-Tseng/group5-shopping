# group5-shopping

| [Code目錄位置](#1) | [前台網頁](#2) | 後台網頁 | ER Model | DB欄位 | Git版控 |
| ----------------------- |:---------------:| ---- | -------- | ---- | ----- |


## <span id="1">1.Code目錄位置</span>

## <span id="2">2.前台網頁</span>

#### 購物商城首頁

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E8%B3%BC%E7%89%A9%E5%95%86%E5%9F%8E%E9%A6%96%E9%A0%81.JPG" width="800"/> </div>

#### 點擊加入購物車按鈕，快速加入商品

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E9%A6%96%E9%A0%81%E9%BB%9E%E6%93%8A%E5%8A%A0%E5%85%A5%E8%B3%BC%E7%89%A9%E8%BB%8A.jpg" width="800"/> </div>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E5%8A%A0%E5%85%A5%E8%B3%BC%E7%89%A9%E8%BB%8A%E6%88%90%E5%8A%9F.JPG" width="800"/> </div>

#### Ajax加入購物車商品

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

#### 進入商品頁面，調整數量加入購物車

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E5%95%86%E5%93%81%E9%A0%81%E9%9D%A2%E8%AA%BF%E6%95%B4%E6%95%B8%E9%87%8F%E5%8A%A0%E5%85%A5%E8%B3%BC%E7%89%A9%E8%BB%8A.JPG" witth="800"/> </div>

#### 修改購物車內商品數量與刪除

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E9%80%B2%E5%85%A5%E8%B3%BC%E7%89%A9%E8%BB%8A%E9%A0%81%E9%9D%A2.JPG" width="800"/> </div>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E4%BF%AE%E6%94%B9%E5%95%86%E5%93%81%E6%95%B8%E9%87%8F.JPG" width="800"/> </div>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E5%88%AA%E9%99%A4%E5%95%86%E5%93%81.JPG" width="800"/> </div>

#### [購物車codelink](https://github.com/Lewis-Tseng/group5-shopping/blob/master/src/com/shopping/controller/Shopping_ProductServlet.java)

#### 商品結帳頁面

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E5%95%86%E5%93%81%E7%B5%90%E5%B8%B3%E9%A0%81%E9%9D%A2-%E4%BD%BF%E7%94%A8%E9%BB%9E%E6%95%B8%E6%89%A3%E6%AC%BE.JPG" width="800"/> </div>

#### 結帳完成並產生訂單

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E7%94%A2%E7%94%9F%E8%B3%BC%E7%89%A9%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

#### [交易行為新增訂單與明細codelink](https://github.com/Lewis-Tseng/group5-shopping/blob/master/src/com/product_order/controller/Product_OrderServlet.java)

#### 搜尋訂單

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>

<div align="center"> <img src="https://github.com/Lewis-Tseng/group5-shopping/blob/master/github_useimages/%E5%89%8D%E5%8F%B0images/%E6%9F%A5%E8%A9%A2%E8%A8%82%E5%96%AE%E5%87%BA%E7%8F%BE%E4%B8%80%E7%AD%86%E8%A8%82%E5%96%AE.JPG" width="800"/> </div>
