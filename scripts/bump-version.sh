#!/bin/bash
# ─────────────────────────────────────────────────────
#  版本号自动递增脚本
#  用法: ./scripts/bump-version.sh [major|minor|patch]
#  示例: ./scripts/bump-version.sh patch  # 2.3.1 → 2.3.2
#       ./scripts/bump-version.sh minor  # 2.3.1 → 2.4.0
#       ./scripts/bump-version.sh major  # 2.3.1 → 3.0.0
# ─────────────────────────────────────────────────────

BUILD_FILE="kotlin-quiz/app/build.gradle.kts"

# 读取当前版本
CURRENT_VER=$(grep 'versionName' "$BUILD_FILE" | sed 's/.*"\(.*\)".*/\1/')
CURRENT_CODE=$(grep 'versionCode' "$BUILD_FILE" | sed 's/.*versionCode\s*=\s*\([0-9]*\).*/\1/')

echo "📌 当前版本: $CURRENT_VER (code=$CURRENT_CODE)"

# 解析版本号
IFS='.' read -ra PARTS <<< "$CURRENT_VER"
MAJOR=${PARTS[0]}
MINOR=${PARTS[1]}
PATCH=${PARTS[2]}

TYPE="${1:-patch}"
case $TYPE in
  major) MAJOR=$((MAJOR+1)); MINOR=0; PATCH=0 ;;
  minor) MINOR=$((MINOR+1)); PATCH=0 ;;
  patch) PATCH=$((PATCH+1)) ;;
  *) echo "❌ 无效参数: $TYPE (支持: major/minor/patch)"; exit 1 ;;
esac

NEW_VER="$MAJOR.$MINOR.$PATCH"
NEW_CODE=$((CURRENT_CODE + 1))
NOW=$(date +"%Y-%m-%d %H:%M")

echo "🚀 新版本: $NEW_VER (code=$NEW_CODE)"

# 更新 build.gradle.kts
sed -i "s/versionCode = $CURRENT_CODE/versionCode = $NEW_CODE/" "$BUILD_FILE"
sed -i "s/versionName = \"$CURRENT_VER\"/versionName = \"$NEW_VER\"/" "$BUILD_FILE"

# 更新 AboutScreen.kt
ABOUT_FILE="kotlin-quiz/app/src/main/java/com/quizhelper/app/ui/about/AboutScreen.kt"
sed -i "s/v$CURRENT_VER/v$NEW_VER/" "$ABOUT_FILE"

# 更新 CHANGELOG.md（插入新版本顶部）
CHANGE_FILE="CHANGELOG.md"
echo -e "## [$NEW_VER] — $NOW\n\n### 变更\n- \n\n$(cat $CHANGE_FILE)" > "$CHANGE_FILE"

echo ""
echo "✅ 版本已更新: $CURRENT_VER → $NEW_VER"
echo "📝 请编辑 CHANGELOG.md 补充分类 (🎨 UI/🚀 新增/🐛 修复/🔧 技术改进)"
echo "💬 git commit 示例: \"feat: 新功能说明，版本$NEW_VER\""
