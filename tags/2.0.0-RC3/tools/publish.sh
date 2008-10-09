#!/bin/bash
# Release publishing tool for Squiggle

BASE=$(dirname $0)
export VERSION=${1:?No version number given}
export TAG=$VERSION

REPOSITORY=`svn info | grep 'Repository Root:' | cut -c 18-`
WORKING_DIR=build/release
EXPORT_SUBDIR=squiggle-sql-$VERSION

function export_release() {
    svn export $REPOSITORY/tags/$VERSION $EXPORT_SUBDIR
    if [ $? -ne 0 ]; then
        exit 1
    fi
}

function build_release() {
    ant -Dversion=$VERSION
    if [ $? -ne 0 ]; then
        exit 1
    fi
}

function publish_file() {
    $BASE/googlecode_upload.py \
	--project=squiggle-sql \
	--summary="$2" \
	--labels="Featured" \
	$1 

    if [ $? -ne 0 ]; then
        exit 1
    fi	
}

function publish_release() {
    publish_file build/squiggle-sql-$VERSION.jar "Library only"
    publish_file build/squiggle-sql-$VERSION-src.zip "Source archive for IDEs"
    publish_file build/squiggle-sql-$VERSION.zip "Full distribution in ZIP format"
    publish_file build/squiggle-sql-$VERSION.tgz "Full distribution in TGZ format"
}

echo "Publishing release of Squiggle SQL $VERSION"
rm -rf $WORKING_DIR
mkdir -p $WORKING_DIR
cd $WORKING_DIR
export_release
cd $EXPORT_SUBDIR
build_release
publish_release
