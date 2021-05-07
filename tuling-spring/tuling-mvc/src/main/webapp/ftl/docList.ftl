<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>tuling blog</title>
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/semantic-ui/2.3.1/semantic.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/semantic-ui/2.3.1/semantic.min.js"></script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/simplemde/latest/simplemde.min.css">
    <script src="https://cdn.jsdelivr.net/simplemde/latest/simplemde.min.js"></script>

    <style type="text/css">
        .text.describe {
            text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;
            width: 500px;
            height: 50px;
        }

    </style>
</head>
<body>


<div class="top ui segment">
    <div class="ui secondary  menu">
        <div class="ui container">
            <a class=" item">
                LOGO
            </a>
            <div class="item">
                <div class="ui mini icon input">
                    <input type="text" placeholder="搜索...">
                    <i class="search link icon"></i>
                </div>
            </div>
            <a class="active primary item" href="/list">
                首页
            </a>
            <a class="item" href="/list?author=鲁班">
                鲁班专栏
            </a>
            <a class="item" href="/list?author=悟空">
                悟空专栏
            </a>
            <a class="item" href="/list?author=诸葛">
                诸葛专栏
            </a>
            <div class="right menu">
                <a class="ui dropdown icon item" href="/edit?user=鲁班">
                    <i class="add icon"></i>
                </a>

                <a class="ui icon dropdown item">
                    <i class="user icon"></i>
                </a>
            </div>
        </div>
    </div>
</div>


<!--内容主体-->
<div class="ui   container segment ">
    <!-- 中间内容 -->
    <div class="ui divided items">
        <#list docs as doc>
            <div class="item">
                <div class="content">
                    <a class="header">${doc.title}</a>
                    <div class="meta">
                        11
                    </div>
                    <div class="description">
                        <p class="text describe">
                            ${doc.content}
                        </p>
                    </div>
                    <div class="extra">
                        <div class="ui right floated  ">
                            ${doc.author} 最后更新于 ${doc.createTime?string('yyyy-MM-dd hh:mm:ss')}
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</div>

<script type="text/javascript">
    var simplemde = new SimpleMDE({element: $("#doc-edit")[0]});
</script>
</body>
</html>