# 变更日志

## [2.0.0] — 2026-06-06

### ✨ 新增功能

#### 错题集系统
- **自动记录错题**：练习和考试中答错的题目自动加入错题集
- **错题集页面**：底部导航新增「错题」入口，首页显示错题数量概览
- **错题详情**：点击错题可查看详情（题目、正确选项高亮、答案、解析）
- **错题练习**：支持随机练习和顺序练习错题，答对自动移出错题集
- **数据联动**：重新导入题库时，外键 CASCADE 自动清空错题集

#### 练习模式拆分
- **顺序练习**：按照题库原始顺序逐题练习
- **随机练习**：题目随机打乱顺序练习
- 首页两个独立入口按钮，路由带 `practiceType` 参数

#### 分数体系重构
- **得分与正确率分离**：`QuizResult` 新增 `correctRate` 字段
- **练习**：显示「X分」（正确题数）+ 下方「正确率 X%」
- **考试**：显示「X分」（分数制）+ 下方「正确率 X%」
- 历史记录列表/详情同步展示得分和正确率
- `ScoreCircle` 组件改为显示「X分」而非「X/Y」

#### 考试交互优化
- **交卷按钮**：从底部栏移至右上角 TopAppBar 区域
- **不可改选项**：考试中作答后锁定，不可修改（ViewModel + UI 双层守卫）
- **不显示解析**：考试模式不展示题目解析，仅练习模式显示

#### 练习交互优化
- **题目解析**：顺序/随机练习均展示对错反馈 + 答案解析
- **自动提交**：单选题/判断题点击选项即自动提交并显示反馈
- **简化底部栏**：移除「提交答案」按钮，统一使用「下一题 →」按钮
- MULTIPLE 题点「下一题」自动提交后再翻页

### 🎯 题型识别改进

不再依赖 Excel 中的「题型」列，改为纯数据驱动：

| 条件 | 判定 |
|------|------|
| 有效选项数 ≤ 2 | **判断题** |
| 有效选项数 > 2 且仅 1 个正确答案 | **单选题** |
| 其他（多个正确答案） | **多选题** |

### 🔧 技术改进

- 新增 `WrongQuestion` Room 实体表（FK→questions CASCADE）
- 数据库版本 1 → 2（`fallbackToDestructiveMigration`）
- 导航系统：新增 `wrong_questions` 和 `wrong_detail/{questionId}` 路由
- 底部导航：从 3 栏扩展为 4 栏（首页/错题/历史/设置）
- GitHub Actions：新增原生 Android 构建 job + 自动 Release

### 📄 文档

- [docs/android-native-architecture.md](docs/android-native-architecture.md) — 完整架构文档，覆盖：
  - 错题集模块（Entity/DAO/ViewModel/Screen）
  - 题型自动识别策略
  - 分数分离展示设计
  - 路由参数说明（`practiceType`、`source`）
  - 5 张 Room 表结构

---

## [1.0.0] — 2026-06-04

### 初始发布

- Kotlin + Jetpack Compose 原生 Android 客户端
- MVVM 架构（AndroidViewModel + Flow + Compose）
- Room 本地数据库（4 张表：questions、question_bank_meta、history_records、history_details）
- 自研 Excel 解析器（ZipInputStream + SAXParser，无 Apache POI）
- 练习模式（随机出题、即时反馈、答题网格跳转）
- 考试模式（单 60 + 多 100 + 判 40 组卷、100 分钟限时、每题 0.5 分）
- 历史记录（列表/详情/题型筛选）
- 自定义 Material 3 主题（6 色系）
- 底部 3 栏导航（首页/历史/设置）
- 支持 Android 8.0+ (API 26)
- Capacitor Android WebView 壳工程同步构建
