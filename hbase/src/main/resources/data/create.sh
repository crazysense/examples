#!/bin/sh

echo "create 'test', 'cf'" | hbase shell
echo "put 'test', 'row1', 'cf:a', 'value1'" | hbase shell
echo "put 'test', 'row2', 'cf:b', 'value2'" | hbase shell
echo "put 'test', 'row3', 'cf:c', 'value3'" | hbase shell