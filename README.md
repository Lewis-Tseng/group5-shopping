# group5-shopping
<div align="center">
| [Code目錄位置](#1) | [前台網頁](#2) | 後台網頁 | ER Model | DB欄位 | Git版控 |
| ----------------------- |:---------------:| ---- | -------- | ---- | ----- |
</div>

## <span id="1">1.Code目錄位置</span>

## <span id="2">2.前台網頁</span>

#### 購物商城首頁

`<div align="center"> <img src=""/> </div>`

#### 點擊加入購物車按鈕，快速加入商品

`<div align="center"> <img src=""/> </div>`

`<div align="center"> <img src=""/> </div>`

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

#### [購物車codelink]()

#### 進入商品頁面，調整數量加入購物車

<div align="center"> <img src=""/> </div>

#### 修改購物車內商品數量與刪除

`<div align="center"> <img src=""/> </div>`

`<div align="center"> <img src=""/> </div>`

`<div align="center"> <img src=""/> </div>`

#### [購物車codelink]()

#### 商品結帳頁面

`<div align="center"> <img src=""/> </div>`

#### 結帳完成並產生訂單

`<div align="center"> <img src=""/> </div>`

#### [交易行為新增訂單與明細codelink]()

#### 搜尋訂單

`<div align="center"> <img src=""/> </div>`

`<div align="center"> <img src=""/> </div>`
