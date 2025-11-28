#!/usr/bin/env pwsh
<#
Compile all Java files under src/ to target/classes and run the Main class.
Usage: .\run.ps1
Requires: JDK (javac + java) available on PATH
#>
if (-not (Get-Command javac -ErrorAction SilentlyContinue)) {
    Write-Error "javac not found on PATH. Please install JDK and ensure javac is available."
    exit 1
}

Remove-Item -Recurse -Force -ErrorAction SilentlyContinue target
New-Item -ItemType Directory -Path target/classes | Out-Null

$sources = Get-ChildItem -Path . -Recurse -Filter *.java | ForEach-Object { $_.FullName }
if ($sources.Count -eq 0) { Write-Error "no java sources found under src/"; exit 1 }

$tmp = Join-Path $PWD 'sources.txt'
$sources | Out-File -FilePath $tmp -Encoding UTF8

Write-Host "Compiling ${sources.Count} Java files..."
Start-Process -FilePath javac -ArgumentList "-d","target/classes","@$tmp" -NoNewWindow -Wait
if ($LASTEXITCODE -ne 0) { Write-Error "javac failed"; exit $LASTEXITCODE }

Write-Host "Running application..."
Start-Process -FilePath java -ArgumentList "-cp","target/classes","app.Main" -NoNewWindow -Wait
